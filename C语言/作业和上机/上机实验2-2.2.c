#include <stdio.h>
#include <math.h>
int main()
{
	double	x1;                             /* x1,x2分别为方程的2个解 */
	double	x2;
	double	melt;
	int	a;
	int	b;                              
	int	c;
	scanf( "%d %d %d", &a, &b, &c );        
	melt = b * b - 4 * a * c;               //公式
	if ( melt > 0 )
	{
		x1	= (-b + sqrt( melt ) ) / (2 * a);
		x2	= (-b - sqrt( melt ) ) / (2 * a);
		printf( "x1 = %lf,x2 = %lf\n", x1, x2 );
	}
	else if ( melt == 0 )
	{
		x1	= (-b) / (2 * a);
		x2	= x1;
		printf( "%lf,%lf\n", x1, x2 );
	}
	else
		{
		printf( "not real roots\n" );
	}
}


