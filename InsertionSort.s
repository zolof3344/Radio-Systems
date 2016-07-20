!this is code for implementing an insertion sort in sparc assembly
.data

.align 4

a: .word 3,4,2,10,1,5,12,6,13,14,15,16 !store word array at place A

length: .word 12 !length of array

b: .word 25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1 !store array in B

otherLength: .word 25 !length of other array

.text

.global main

main: !main program

set a,%o0 !set A to the out register

set length,%o1 !set length to the out register

save !save the registers

call sort !call sort

nop !nop

set b,%o0 !set o0 to b

set otherLength,%o1 !set o1 to length

save !save the registers

call sort !call sort

nop !nop

break1: !break point

mov 0,%g0

done:

mov 0,%g0

sort:



init:

mov %i0,%l0 !move the value of A to l0

ld [%i1],%l1 !make the value of l1 equal o the first value of A

mov 0,%l2 !use l2 as the index of the outer loop

mov 1,%g3 !outer counter

mov 0,%g4 !inner counter

mov 0,%l6 !offset for inner ounter

add 1,%l1,%l1 !add 1 to l1 for the proper sort

loopOuter:

cmp %g3,%l1 !compare them

bl cont !branch to continue if unfinished

nop

ba finished !branch to finished if finished

cont:

add %g3,1,%g3 !increment the counter



sub %g3,1,%g4 !second counter equals counter minus 1

sub %l2,4,%l3 !same thing as inner counter, inner counter isn&#39;t used in version 2.0

ld [%l0+%l2],%l4 !load the current element of the array to be the pivot

add %l2,4,%l2 !move the pointer one for the next time we enter the route

loopInnerInit:

test1: !test for inner loop

cmp %l3,-4 !if the count is too low stop

bge test2 !if it isn&#39;t too low branch to test2

nop

ba endOuterLoop !go to the end of outer loop

nop

test2: !test2 compare the current one with the pivot

ld [%l0+%l3],%l5 !load the current value into l5

cmp %l5,%l4 !compare em

bg innerLoop !branch if the current value is greater htan the pivot

nop !nop

ba endOuterLoop !end the loop

innerLoop: !inner loop fills the delay slot before we branch to outer loop

add %l3,4,%l6 !add 4 to l3 so the pointer of l6 is l3+4 (the next value)

st %l5,[%l0+%l6] !store l5 in the next value since its greater

sub %l3,4,%l3 !sub 4 from l3

ba test1 !go back to the tests

nop

endOuterLoop:

st %l4,[%l0+%l6] !store l4 in l3 + 4

ba loopOuter !loop to the outer loop

nop !nop

finished: !if done do an arbitrary insstruction

mov 0,%g0