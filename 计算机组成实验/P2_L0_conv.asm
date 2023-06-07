
.text
    # read m1
    li $v0, 5
    syscall
    add $s1, $v0, $0 # $s1 holds m1

    # read n1
    li $v0, 5
    syscall
    add $s2, $v0, $0 # $s2 holds n1

    # read m2
    li $v0, 5
    syscall
    add $s3, $v0, $0 # $s3 holds m2

    # read n2
    li $v0, 5
    syscall
    add $s4, $v0, $0 # $s4 holds n2

    # allocate memory for matrix f
    mul $t0, $s1, $s2 # t0 = m1*n1
    sll $a0, $t0, 2   # memory size = m1*n1*4
    li $v0, 9         # sbrk
    syscall
    add $s5, $v0, $0 # $s5 holds matrix f address

    # allocate memory for matrix h
    mul $t0, $s3, $s4 # t0 = m2*n2
    sll $a0, $t0, 2   # memory size = m2*n2*4
    li $v0, 9         # sbrk
    syscall
    add $s6, $v0, $0 # $s6 holds matrix h address

    # m1-m2+1
    sub $t0, $s1, $s3 
    addi $t6, $t0, 1   # $t6 holds rows
    # n1-n2+1
    sub $t0, $s2, $s4
    addi $t7, $t0, 1   # $t7 holds columns
    # allocate memory for result
    mul $t0, $t6, $t7 # t0 = rows*columns
    sll $a0, $t0, 2   # memory size = rows*columns*4
    li $v0, 9         # sbrk
    syscall
    add $s0, $v0, $0 # $s0 holds matrix h address

    # read matrix f
    mul $t2, $s1, $s2 # count = m1*n1
    add $t5, $s5, $0  # $t5 holds A
while1:
    li $v0, 5          # read int
    syscall
    sw $v0, 0($t5)      # save int
    addi $t5, $t5, 4    # move to next word
    addi $t2, $t2, -1   # count-1
    bne $t2, $0, while1 # repeat loop
end_while1:

    # read matrix k
    mul $t2, $s3, $s4    # count = m2*n2
    add $t5, $s6, $0     # $t5 holds B
while2:
    li $v0, 5           # read int
    syscall
    sw $v0, 0($t5)      # save int
    addi $t5, $t5, 4    # move to next word
    addi $t2, $t2, -1   # count-1
    bne $t2, $0, while2 # repeat loop
end_while2:

    # convolution
    add $t8, $t6, $0    # $t8 holds row
    li $t1, 0           # i = 0
for1:
    slt $t0, $t1, $t8   # i < rows
    beq $t0, $0, end_for1
    li $t2, 0            # j = 0
for2:
    slt $t0, $t2, $t7   # j < columns
    beq $t0, $0, end_for2
    li $t3, 0           # k1 = 0
    li $s7, 0           # sum = 0
for3:
    slt $t0, $t3, $s3  # k1 < m2
    beq $t0, $0, end_for3 # if k1 >= m2, exit loop
    li $t4, 0          # k2 = 0
for4:
    slt $t0, $t4, $s4  # k2 < n2
    beq $t0, $0, end_for4 # if k2 >= n2, exit loop
    # f[i+k1][j+k2]
    add $t0, $t1, $t3   # $t0 = i+k1
    mul $t0, $t0, $s2   # (i+k1)*n1
    add $t0, $t0, $t2   # (i+k1)*n1+j
    add $t0, $t0, $t4   # (i+k1)*n1+j+k2
    sll $t0, $t0, 2     # ((i+k1)*n1+j+k4)*4
    add $t0, $t0, $s5   # &f[i+k1][j+k2]
    lw $t5, 0($t0)      # $t5 = f[i+k1][j+k2]
    # h[k1][k2]
    mul $t0, $t3, $s4   # k1*n2
    add $t0, $t0, $t4   # k1*n2+k2
    sll $t0, $t0, 2     # (k1*n2+k2)*4
    add $t0, $t0, $s6   # &h[k1][k2]
    lw $t6, 0($t0)      # $t6 = h[k1][k2]

    # f[i+k1][j+k2]*h[k1][k2]
    mul $t0, $t5, $t6   
    add $s7, $s7, $t0   # sum += f[i+k1][j+k2]*h[k1][k2]
    addi $t4, $t4, 1    # k2++
    j for4
end_for4:
    addi $t3, $t3, 1    # k1++
    j for3
end_for3:
    # result[i][j] = sum
    mul $t0, $t1, $t7  
    add $t0, $t0, $t2 
    sll $t0, $t0, 2     # &result[i][j]
    add $t0, $t0, $s0 
    sw  $s7, 0($t0)     # result[i][j] = sum
    addi $t2, $t2, 1    # j++
    j for2
end_for2:
    addi $t1, $t1, 1    # i++
    j for1
end_for1:

    # m1-m2+1
    sub $t0, $s1, $s3 
    addi $t6, $t0, 1   # $t6 holds rows
    # n1-n2+1
    sub $t0, $s2, $s4
    addi $t7, $t0, 1   # $t7 holds columns
    # print result
    li $t1, 0           # i =0
loop1:
    slt $t0, $t1, $t6    # $t0 = i < t6
    beq $t0, $0, end_loop1 # if i >= n, exit loop1
    li $t2, 0           # j =0
loop2:
    slt $t0, $t2, $t7  # $t0 = j < t7
    beq $t0, $0, end_loop2 # if i >= n, exit loop1

    mul $t0, $t1, $t7  
    add $t0, $t0, $t2 
    sll $t0, $t0, 2     # &result[i][j]
    add $t0, $t0, $s0 

    lw $a0, 0($t0)    # load an integer
    li $v0, 1
    syscall           # print int

    add $t0, $t7, -1   # t1 = n-1
    slt $t0, $t2, $t0  # j < n-1
    beq $t0, $0, next
    li $a0, 32         # space =  32
    li $v0, 11         # print space
    syscall
next:
    addi $t2, $t2, 1  # j+=1
    j loop2
end_loop2:
    li $a0, 10         # ln = 10
    li $v0, 11         # print new line
    syscall
    addi $t1, $t1, 1  # i+=1
    j loop1
end_loop1:


    li $v0, 10         # exit program
    syscall
