str = input().lower()
vowels = ('a', 'o', 'y', 'e', 'u', 'i')
for i in str:
	if not i in vowels:
		print(f".{i}", end="")
print("")
