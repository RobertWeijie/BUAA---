.data 
symbol: .space 28 # 4*7
array: .space  28 # 4*7
n: .word 0
.text
    # read n
    li $v0, 5        # read int
    syscall
    la $t0, n        # load n's adderss
    sw $v0, 0($t0)   # save n

    li $a0, 0
    jal FullArray
    # exit program
    li $v0, 10
    syscall
FullArray:
    addi $sp, $sp, -16 # allocate stack memory
    sw $ra, 0($sp)     # save $ra 
    sw $s0, 4($sp)     # $s0 holds index
    sw $s1, 8($sp)     # $s1 holds i
    sw $s2, 12($sp)    # $s2 holds symbol

    add $s0, $a0, $0
    la $t1, n 
    lw $t2, 0($t1)     # load n
    slt $t0, $s0, $t2,  # t0 = index < n
    bne  $t0, $0, next
    li $s1, 0          # i = 0
    la $t5, array
for1:
    la $t1, n 
    lw $t2, 0($t1)     # load n
    slt $t0, $s1, $t2  # i < n
    beq $t0, $0, end_for1
    lw $a0, 0($t5)     # load an integer
    li $v0, 1          # print integer
    syscall

    li $a0, 32         # space char
    li $v0, 11         # print space
    syscall

    addi $t5, $t5, 4    # move to next word
    addi $s1, $s1, 1    # i++
    j for1
end_for1:
    li $a0, 10          # new line char = 10
    li $v0, 11          # print new line 
    syscall
    j quit
next:
    li $s1, 0          #  i = 0
    la $s2, symbol
for2:
    la $t1, n 
    lw $t2, 0($t1)     # load n
    slt $t0, $s1, $t2  # i < n
    beq $t0, $0, quit

    lw $t0, 0($s2)     # load symbol[i]
    bne $t0, $0, skip  # check symbol[i] == 0
    sll $t1, $s0, 2    # index*4
    la $t2, array
    add $t1, $t1, $t2  # &array[index]
    add $a0, $s1, 1    # i+1
    sw $a0, 0($t1)     # array[index] = i+1
    li $t4, 1
    sw $t4, 0($s2)     # symbol[i] = 1
    
    addi $a0, $s0, 1   # index+1
    jal FullArray
    li $t4, 0
    sw $t4, 0($s2)     # symbol[i] = 1
skip:
    addi $s2, $s2, 4    # move to next symbol
    addi $s1, $s1, 1    # i ++
    j for2

quit:
    # restore register
    lw $s2, 12($sp)
    lw $s1, 8($sp)
    lw $s0, 4($sp)
    lw $ra, 0($sp)
    addi $sp, $sp, 16  # free stack memory
    jr $ra             # function return
