#include <iostream>

using namespace std;

int GCD(int a, int b, int &x, int &y) {
    if(b == 0) {
       x = 1;
       y = 0;
       return a;
    }

    int x1, y1, gcd = GCD(b, a % b, x1, y1);
    x = y1;
    y = x1 - (a / b) * y1;
    return gcd;
}

int main()
{
	int a, b, x, y, gcd;
	cin >> a >> b;
	
	bool flag = false;
	if(a < b) {
	    swap(a, b);
	    flag = true;
	}
	gcd = GCD(a, b, x, y);
	
	if (flag) swap(x, y);
	cout << gcd << " " << x << " " << y;
	
	return 0;
}
