.data
txt1: .asciiz " /\\ "
txt2: .asciiz "/__\\"
.text
    li $v0, 5        # read int
    syscall
    add $s5, $v0, $0  # s5 =total

    li $a0, 2
    add $a1, $s5, $0  # total
    jal qpow
    add $s6, $v0, $0  # s6 = n
    li $s4, 0 #  i = 0
for:
    bge $s4, $s6, end_for #i < n
    # i
    add $a0, $s4, $0
    # total
    add $a1, $s5, $0
    jal dfs
    li $a0, 10
    li $v0, 11
    syscall

    addi $s4, $s4, 1 # i=i+1
    j for
end_for:

    li $v0, 10
    syscall

qpow:
# $a0-base
# $a1-count
    li $t1, 1 # result=1
qpow_loop:
    beq $a1, $0, end_qpow_loop  # count != 0
    # 测试odd
    andi $t0, $a1, 1
    beq $t0, $0, next # is even
    mul $t1, $t1, $a0 # result*base
next:
    mul $a0, $a0, $a0  # base=base*base
    srl $a1, $a1, 1    # count = count/2
    j qpow_loop
end_qpow_loop:
    add $v0, $t1, $0   # return result 
    jr $ra

dfs:
    addi $sp, $sp, -20 # allocate stack memory
    sw $ra, 0($sp)     # save $ra 
    sw $s0, 4($sp)     # $s0 holds index
    sw $s1, 8($sp)     # $s1 holds i
    sw $s2, 12($sp)    # $s2 holds symbol
    sw $s3, 16($sp)
#$a0- level
#$a1- size
    add $s0, $a0, $0   # s0 = level
    add $s1, $a1, $0   # s1 = size

    li $t0, 1
    bne $s1, $t0, else # size == 1
    bne $s0, $0, else0 # level == 0
    # print " /\\ "
    la $a0, txt1
    li $v0, 4
    syscall
    j quit_dfs
else0:
    # print "/__\\"
    la $a0, txt2
    li $v0, 4
    syscall
    j quit_dfs
else:
    li $a0, 2
    addi $a1, $s1, -1 # size-1
    jal qpow
    add $s3, $v0, $0  # $s3 =  buf
    # if level < buf
    bge $s0, $s3, else2
    li $s2, 0  # i = 0
while1:
    bge $s2, $s3, end_while1 #i<buf 就跳转去end while1 这个标签
    li $a0, ' '
    li $v0, 11
    syscall     # print ' '
    addi $s2, $s2, 1  # i++
    j while1
end_while1:
    #level
    add $a0, $s0, $0
    # size-1
    addi $a1, $s1, -1
    jal dfs
    li $s2, 0  # i =0
while2:
    bge $s2, $s3, end_while2
    li $a0, ' '
    li $v0, 11
    syscall     # print ' '
    addi $s2, $s2, 1  # i++
    j while2
end_while2:

    j quit_dfs
else2:
    # level-buf
    sub $a0, $s0, $s3 
    # size-1
    addi $a1, $s1, -1
    jal dfs
    # level-buf
    sub $a0, $s0, $s3 
    # size-1
    addi $a1, $s1, -1
    jal dfs
quit_dfs:
    lw $s3, 16($sp)
    lw $s2, 12($sp)
    lw $s1, 8($sp)
    lw $s0, 4($sp)
    lw $ra, 0($sp)
    addi $sp, $sp, 20  
    jr $ra
