#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Node {
	int	PN; //parent node
	int	childnode; //count childnode
	int	count_ancestor; //count 조상
	int	count_descendant; //count 자손
	vector<int> node;
};
#define total 200001

Node node[total];
int	happy[total];

bool	compare(Node a, Node b)
{
	if (a.count_ancestor - a.count_descendant < b.count_ancestor - b.count_descendant)
		return (false);
	else
		return (true);
}

void	dfs_find_parent(int parent, int n)
{
	node[n].PN = parent;
	for (vector<int>::iterator iter = node[n].node.begin(); iter != node[n].node.end(); iter++)
	{
		if (parent != *iter)
		{
			dfs_find_parent(n, *iter);
			node[n].childnode++;
		}
	}
}

int		dfs_happiness(int ancestor, int n)
{
	node[n].count_ancestor = ancestor;
	for (vector<int>::iterator iter = node[n].node.begin(); iter != node[n].node.end(); iter++)
	{
		if (node[n].PN != *iter)
		{
			node[n].count_descendant += dfs_happiness(ancestor + 1, *iter);
		}
	}
	return (node[n].count_descendant + 1);
}

int main()
{
	int n, k, u, v;

	cin >> n >> k;
	for (int i = 0; i < n - 1; i++)
	{
		cin >> u >> v;
		node[u].node.push_back(v);
		node[v].node.push_back(u);
	}
	dfs_find_parent(0, 1);
	dfs_happiness(0, 1);
	for (int i = 1; i <= n; i++)
	{
		happy[i - 1] = node[i].count_ancestor - node[i].count_descendant;
		//cout << happy[i - 1] << endl;
	}
	sort(happy, happy + n);
	long long ret = 0;
	for (int i = 0; i < k; i++)
	{
		ret += happy[n - 1 - i];
	}
	cout << ret << endl;
}
