.data
line: .space 32     # line buffer
buffer: .space 32
.text
    # read n
    li $v0, 5        # read int
    syscall
    add $s1, $v0, $0 # $s1 holds n

    li $s0, 0       # i = 0
    la $s2, buffer  # $s2 hold buffer
for:
    slt $t0, $s0, $s1 # $t0 = i < n
    beq $t0, $0, end_for
    # read a line
    la $a0, line    # line buffer
    li $a1, 128     # max number
    li $v0, 8       # read a line
    syscall

    lb $t1, 0($a0)   # just need a character
    sb $t1, 0($s2)   # save a character
    addi $s2, $s2, 1 # buffer
    addi $s0, $s0, 1 # i++
    j for
end_for:

    # judge
    la $s2, buffer     # start = buffer address
    li $s4, 1          # result = 1
    add $s3, $s2, $s1  # buffer+n
    addi $s3, $s3, -1  # end = buffer+n-1
for2:
    slt $t0, $s2, $s3  # start < end
    beq $t0, $0, print
    lb $t1, 0($s2)     # load *start
    lb $t2, 0($s3)     # load *end
    bne $t1, $t2, set0
    addi $s3, $s3, -1  # end-1
    addi $s2, $s2, 1   # start+1
    j for2
set0:
    li $s4, 0          # result = 0
print:
    add $a0, $s4, 0    # print result
    li $v0, 1          # print int
    syscall

    li $v0, 10         # exit program
    syscall
