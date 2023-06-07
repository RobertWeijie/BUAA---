.data
arr: .space  2000 #500*4
.text
    li $v0, 5
    syscall
    add $s0, $v0, $0 # $s0=n

    li $t0, 0  # i = 0
    la $s1, arr
while1:
    bge $t0, $s0, end_while1  # i < n
    li $v0, 5
    syscall   # get int
    sw $v0, 0($s1) # arr[i] = getint()  sw在这里就是存储字的意思

    addi $s1, $s1, 4   # arr+4
    addi $t0, $t0, 1  #  i=i+1
    j while1
end_while1:

    li $t0, 0 #  i = 0
    addi $s5, $s0, -1  # $s5=n-1  这个我们可以看到s5 = n-1 因为s0 我们在上面定义的是n，利用addi的立即数就是n-1
while2:
    # i < n-1
    bge $t0, $s5, end_while2 #s5 =n-1 如果i大于n-1 直接跳到end while 2这个标签
    li $t1, 0   # j = 0
    la $s3, arr # $s3=arr 地址
while3:
    # j+1
    add $t5, $t1, 1
    # n-i
    sub $t6, $s0, $t0
    # j+1 < n-i
    bge $t5, $t6, end_while3 #j+1大于n-1的话直接跳到end while 3这个标签
    lw $t2, 0($s3) # $t2 = arr[i]
    lw $t3, 4($s3) # $t3 = arr[i+1]
    bge $t2, $t3, skip
    #交换 arr[i]和rr[i+1]
    sw $t2, 4($s3) 
    sw $t3, 0($s3) 
skip:
    addi $s3, $s3, 4  # arr=arr+4
    addi $t1, $t1, 1 # j=j+1
    j while3
end_while3:
    addi $t0, $t0, 1  # i=i+1
    j while2
end_while2:

    li $t0, 0  # i = 0
    la $s1, arr
for:
    bge $t0, $s0, end_for  # i < n
    lw $a0, 0($s1) # arr[i]
    li $v0, 1
    syscall   # print int

    li $a0, 10 # 换行
    li $v0, 11
    syscall

    addi $s1, $s1, 4   # arr+4
    addi $t0, $t0, 1  #  i=i+1
    j for
end_for:

    li $v0, 10
    syscall