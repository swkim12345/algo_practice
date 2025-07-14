#include <iostream>

using namespace std;
typedef long long ll;

typedef struct s_string{
	char c;
	t_string *prev;
	t_string *next;
}	t_string;

typedef struct s_list{
	t_string *head;
	t_string *tail;
	t_string *cursor;
} t_list;

# define total 100001

char tmp[total];
t_list *list;

void push()

int main()
{
	cin >> tmp;
	for (int i = 0; i < total; i++)
	{
		if (tmp[i] == '\0')
			break;
		t_string *str = new t_string;
		str->c = tmp[i];

	}
}
