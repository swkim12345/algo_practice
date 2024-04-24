#include <iostream>
#include <vector>
#include <array>

int main() {
    std::vector<std::array<long long, 2>> vec;
    vec.push_back({1, 2});
    vec.push_back({3, 4});

    for (const auto& item : vec) {
        std::cout << "First value: " << item[0] << ", Second value: " << item[1] << std::endl;
    }

    return 0;
}
