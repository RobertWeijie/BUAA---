```
 1 .data
 2     prompt1: .asciiz "Enter the number\n"
 3     prompt2: .asciiz "The factorial of n is:\n"
 4 
 5 .text
 6     # Print prompt1
 7     li $v0, 4
 8     la $a0, prompt1
 9     syscall
10 
11     # Read integer
12     li $v0, 5
13     syscall
14 
15     # Call factorial
16     move $a0, $v0
17     jal factorial
18     move $a1, $v0 # save return value to a1
19 
20     # Print prompt2
21     li $v0, 4
22     la $a0, prompt2
23     syscall
24 
25     # Print result
26     li $v0, 1
27     move $a0, $a1
28     syscall
29 
30     # Exit
31     li $v0, 10
32     syscall
33 
34     ## Function int factorial(int n)
35     factorial:
36         ## YOUR CODE HERE
37         addi $sp,$sp,-8            #adjust stack for 2 items
38         sw   $ra,4($sp)            #save return address
39         sw   $a0,0($sp)            #save the argument n
40     
41         slti $t0,$a0,1             #if n < 1,then set $t0 as 1
42         beq  $t0,$zero,L1          #if equal,then jump L1
43                                    #above all,if n >= 1,then jump L1
44         #if(n < 1)
45         addi $v0,$zero,1            #return 1
46         addi $sp,$sp,8              #pop 2 items off stack
47         jr   $ra                    #return to caller
48         #else
49         L1:
50             add $a0,$a0,-1          #argument :n - 1
51             jal factorial           #call factorial with (n-1)
52         
53            lw   $a0,0($sp)          #restore argument n
54            lw   $ra,4($sp)          #restore address
55            addi $sp,$sp,8           #adjust stack pionter
56            mul  $v0,$a0,$v0         #return n * factorial(n-1)
57            jr   $ra                  return to caller
58     ## END OF YOUR CODE
59   #jr $ra  
```


##Factorial Psudocode#######
##Fac(){
#  if ($t1==1) return 1;
#  else 
#      return $v0*Factorial($t1-1);
#     }
#cross variable#############
# $t0 -- n
# $t1 -- with initial value n  
# $v0 -- store result
############################
.data
hello: .asciiz "input a number : "
bye : .asciiz "\nbyebye "
.text
li $v0,4
la $a0,hello
syscall
li $v0,5
syscall
move $t0,$v0
move $t1,$t0
li $v0,1
Fac:	addi $sp,$sp,-8
sw $ra,0($sp)
sw $t1,4($sp)
beq $t1,1,re0
addi $t1,$t1,-1
jal Fac
lw $a0, 4($sp) 
mult $v0,$a0
mflo $v0
beq $t1,$t0,end

re0:	addi $t1,$t1,1
lw $ra,0($sp)
addi $sp,$sp,8
jr $ra

end:	move $a0,$v0
li $v0,1
syscall
la $a0,bye
li $v0,4
syscall
li $v0,10
syscall
