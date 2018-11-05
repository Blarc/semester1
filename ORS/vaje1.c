/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <stdio.h>

int ResetBit(int x, int p)
{
    return x &= ~(1 << p);
}

int ResetTwoBits(int x, int p)
{
    return x &= ~(3 << p);
}

int SetBit(int x, int p)
{
    return x |= (1 << p);
}

int SetTwoBitsTo(int x, int p, int n)
{
    x = ResetTwoBits(x, p);
    return x |= (n << p);
}

int main()
{
    printf("Vaje 1.0\n");
    printf("Reset Bit\n");
    printf("1.0 %d\n", ResetBit(0xF, 2));
    printf("1.1 %d\n", ResetBit(0xA, 0));
    printf("Reset Two Bits\n");
    printf("2.1 %d\n", ResetTwoBits(0xFF, 3));
    printf("2.2 %d\n", ResetTwoBits(0xB7, 3));
    printf("Set Bit\n");
    printf("3.1 %d\n", SetBit(0xA, 0));
    printf("3.2 %d\n", SetBit(0xE, 2));
    printf("Set Two Bits To\n");
    printf("4.1 %d\n", SetTwoBitsTo(0xFF, 3, 1));
    printf("4.2 %d\n", SetTwoBitsTo(0xAF, 3, 2));
    
    
    return 0;
}
