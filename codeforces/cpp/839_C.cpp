#include <iostream>
#include <vector>

using namespace std;

struct Node {
	int	Pnode; //parent node
	int	Csize; //child size
	vector<int> Nnode; //near node
};
# define total 100001

Node node[total];


void	dfs_set_parent(int parent, int n)
{
	node[n].Pnode = parent;
	for (vector<int>::iterator iter = node[n].Nnode.begin(); iter != node[n].Nnode.end(); iter++)
	{
		if (*iter != parent)
		{
			dfs_set_parent(n, *iter);
			node[n].Csize++;
		}
	}
}

double	dfs_expect(int n)
{
	double ret;

	ret = 0.0;
	if (node[n].Csize == 0)
		return (0.0);
	for (vector<int>::iterator iter = node[n].Nnode.begin(); iter != node[n].Nnode.end(); iter++)
	{
		if (*iter != node[n].Pnode)
		{
			ret += dfs_expect(*iter) + 1;
		}
	}
	return (ret / node[n].Csize);
}

int main()
{
	int n, s, target;
	cin >> n;
	for (int i = 0; i < n - 1; i++)
	{
		cin >> s >> target;
		node[s].Nnode.push_back(target);
		node[target].Nnode.push_back(s);
	}
	dfs_set_parent(0, 1);
	printf("%.8f\n", dfs_expect(1));
}
