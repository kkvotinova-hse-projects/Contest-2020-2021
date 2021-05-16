#include <iostream>

using namespace std;

long long ExponentiationModulo(long long base, long long degree, long long module) {
  long long answer = 1;
  while (degree != 0) {
    if (degree % 2 == 1) {
      answer = answer * base % module;
    }
    degree /= 2;
    base = base * base % module;
  }
  return answer;
}

void FermatTest(long long input_num) {
  long successful_test = 0, unsuccessful_test = 0;
  bool flag = false;
  for (long long i = 1; i <= input_num - 1; i++) {
    if (ExponentiationModulo(i, input_num - 1, input_num) == 1 ) {
      ++successful_test;
    } else {
      ++unsuccessful_test;
      flag = true;
    }
  }
  if (flag) {
    cout << "Fermat test: False " << unsuccessful_test << " " << successful_test;
  } else {
    cout << "Fermat test: True " << unsuccessful_test << " " << successful_test;
  }
}

long long Pow(long long base, long long degree) {
  long long answer = 1;
  for (int i = 0; i < degree; i++) {
    answer *= base;
  }
  return answer;
}

void MillerRabinTest(long long input_num) {
  long unsuccessful_test = 0, test_1 = 0, test_2 = 0;
  bool flag = true;

  long long s = 0, n = input_num - 1;
  while (n % 2 == 0) {
    ++s;
    n /= 2;
  }
  long long t = n;

  for (long long i = 1; i <= input_num - 1; i++) {
    // Test 1
    long long x = ExponentiationModulo(i, t, input_num);
    if (x == 1) {
      ++test_1;
      continue;
    }

    // Test 2
    bool flag_2 = false;
    for (int j = 0; j < s; j++) {
      x = ExponentiationModulo(i, Pow(2, j) * t, input_num);
      if (x == input_num - 1) {
        ++test_2;
        flag_2 = true;
        break;
      }
    }
    if (flag_2) {
      continue;
    }

    ++unsuccessful_test;
    flag = false;
  }

  if (!flag) {
    cout << "Miller-Rabin test: False " << unsuccessful_test << " " << test_1 << " " << test_2 << endl;
  } else {
    cout << "Miller-Rabin test: True " << unsuccessful_test << " " << test_1 << " " << test_2 << endl;
  }
}

int main() {
  long long input_num;
  cin >> input_num;

  MillerRabinTest(input_num);
  FermatTest(input_num);

  return 0;
}
