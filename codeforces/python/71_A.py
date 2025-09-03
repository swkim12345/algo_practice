size = int(input())
for _ in range(size):
    i = input()
    l = len(i)
    if l > 10:
        print(f"{i[0]}{l - 2}{i[-1]}")
    else:
        print(i)
