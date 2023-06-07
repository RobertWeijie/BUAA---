.text
    # read n
    li $v0, 5
    syscall
    add $s2, $v0, $0 # $s2 holds n


    # allocate heap memory for matrix A
    mul $t0, $s2, $s2 # t0 = n*n
    sll $a0, $t0, 2   # memory size = n*n*4
    li $v0, 9         # sbrk
    syscall
    add $s0, $v0, $0 # $s0 holds matrix A address

    # allocate heap memory for matrix B
    mul $t0, $s2, $s2 # t0 = n*n
    sll $a0, $t0, 2   # memory size = n*n*4
    li $v0, 9         # sbrk
    syscall
    add $s1, $v0, $0 # $s0 holds matrix B address

    # allocate heap memory for matrixC,  mul result
    mul $t0, $s2, $s2 # t0 = n*n
    sll $a0, $t0, 2   # memory size = n*n*4
    li $v0, 9         # sbrk
    syscall
    add $s7, $v0, $0  # $s7 holds matrix C address

    # read matrix A
    mul $t2, $s2, $s2 # count = n*n
    add $t5, $s0, $0  # $t5 holds A
while1:
    li $v0, 5          # read int
    syscall
    sw $v0, 0($t5)      # save int
    addi $t5, $t5, 4    # move to next word
    addi $t2, $t2, -1   # count-1
    bne $t2, $0, while1 # repeat loop
end_while1:
    # read matrix B
    mul $t2, $s2, $s2 # count = n*n
    add $t5, $s1, $0  # $t5 holds B
while2:
    li $v0, 5          # read int
    syscall
    sw $v0, 0($t5)      # save int
    addi $t5, $t5, 4    # move to next word
    addi $t2, $t2, -1   # count-1
    bne $t2, $0, while2 # repeat loop
end_while2:

    li $s3, 0         #  i = 0
    # for(i=0; i<n; i++)
for1:
    slt $t0, $s3, $s2  # $t0 = i < n
    beq $t0, $0, end_for1
    li $s4, 0         # j = 0
    # for(j=0; j<n; j++)
for2:
    slt $t0, $s4, $s2 # $t0 = j < n
    beq $t0, $0, end_for2
    li $s5, 0         # k =0
    li $s6, 0         # sum = 0
    # for(k=0; k<n; k++)
for3:
    slt $t0, $s5, $s2 # $t0 = k < n
    beq $t0, $0, end_for3
    # s3: i
    # s4: j
    # s5: k
    # get A[i][k]
    mul $t0, $s3, $s2 # i*n
    add $t1, $t0, $s5 # i*n+k
    sll $t2, $t1, 2   # $t2 = (i*n+k)*4
    add $t3, $t2, $s0 # the address of A[i][k]
    lw  $t4, 0($t3)   # $t4 holds A[i][k]
    # get B[k][j]
    mul $t0, $s5, $s2 # k*n
    add $t1, $t0, $s4 # k*n+j
    sll $t2, $t1, 2   # $t2 = (k*n+j)*4
    add $t3, $t2, $s1 # the address of B[k][j]
    lw  $t5, 0($t3)   # $t5 holds B[k][j]

    mul $t0, $t4, $t5 # A[i][k]*B[k][j]
    add $s6, $s6, $t0 # sum += A[i][k]*B[k][j]

    addi $s5, $s5, 1  # k=k+1
    j for3
end_for3:
    # the above sum is for C[i][j]
    # store C[i][j]
    mul $t0, $s3, $s2 # i*n
    add $t1, $t0, $s4 # i*n+j
    sll $t2, $t1, 2   # $t2 = (i*n+j)*4
    add $t3, $t2, $s7 # the address of C[i][j]
    sw $s6, 0($t3)    # C[i][j] = sum

    addi $s4, $s4, 1   # j=j+1
    j for2
end_for2:
    addi $s3, $s3, 1   # i=i+1
    j for1
end_for1:

    # print result
    li $s3, 0           # i =0
loop1:
    slt $t0, $s3, $s2    # $t0 = i < n
    beq $t0, $0, end_loop1 # if i >= n, exit loop1
    li $s4, 0           # j =0
loop2:
    slt $t0, $s4, $s2  # $t0 = j < n
    beq $t0, $0, end_loop2 # if i >= n, exit loop1

    mul $t0, $s3, $s2 # i*n
    add $t1, $t0, $s4 # i*n+j
    sll $t2, $t1, 2   # $t2 = (i*n+j)*4
    add $t3, $t2, $s7 # the address of C[i][j]

    lw $a0, 0($t3)    # load an integer
    li $v0, 1
    syscall           # print int

    add $t1, $s2, -1   # t1 = n-1
    slt $t0, $s4, $t1  # j < n-1
    beq $t0, $0, next
    li $a0, 32         # space =  32
    li $v0, 11         # print space
    syscall
next:
    addi $s4, $s4, 1  # j+=1
    j loop2
end_loop2:
    li $a0, 10         # ln = 10
    li $v0, 11         # print new line
    syscall
    addi $s3, $s3, 1  # i+=1
    j loop1
end_loop1:

    li $v0, 10         # exit program
    syscall
