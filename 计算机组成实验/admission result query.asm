.data
buffer: .space 200 #长度小于200字符
sub_str: .space 60 #长度小于 60 字符
txt1: .asciiz "You got in!"
txt2: .asciiz "I am sorry!"
.text

    # 读入一个字符串
    la $a0, buffer    # buffer地址
    li $a1, 200     # 最大长度200
    li $v0, 8
    syscall

    # 读入一个子字符串
    la $a0, sub_str # 子串地址
    li $a1, 60    # 最大长度60
    li $v0, 8
    syscall

    la $s0, buffer # $s0=buffer地址
    li $s5, 10     # 换行符
for1:
    lb $t0, 0($s0) # 取一个字符
    beq $t0, $0, end_for1 # 遇到字符串结尾，退出 
    beq $t0, $s5, end_for1 # 遇到字符'\n'，退出

    la $s1, sub_str # $s1=字串地址 
    addi $s2, $s0, 0 # $s2= $s0
for2:
    lb $t0, 0($s1)  # 子字符串取一个字符 
    beq $t0, $0, find  # 字串遇到结尾，找到了！
    beq $t0, $s5, find  # 字串遇到'\n'，找到了！

    lb $t1, 0($s2)  # buffer商取一个字符
    beq $t1, $0, end_for1 # 遇到字符串结尾，退出
    beq $t1, $s5, end_for1 # 遇到字符'\n'，退出

    bne $t0, $t1, end_for2 # 不相等 退出检查也就是说 $t0不等于t1的话 直接跳到end_for2这个标签

    addi $s1, $s1, 1 # 地址+1
    addi $s2, $s2, 1 # 地址+1
    j for2
end_for2:
    addi $s0, $s0, 1  # buffer+1
    j for1
end_for1:
    # 没有匹配字串，输出信息
    la $a0, txt2 #根据题目 如果不是就输出我们txt2的内容 也就是说 I am sorry
    li $v0, 4 # 这是一个输出字符串的扩展指令 系统调用
    syscall
    j quit
find:
    # 匹配字串，输出信息
    la $a0, txt1 #根据题目 如果匹配成功就输出我们txt1的内容 也就是说 You got in
    li $v0, 4
    syscall
quit:
    li $v0, 10 # 退出程序
    syscall
