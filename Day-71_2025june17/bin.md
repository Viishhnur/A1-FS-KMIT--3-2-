#### rolling hash / sliding window
b = b << 1       :  one shift to left
b &= allOnes        :  removes kth bit, ensures our b always less than 2^k
b |= s\[i] - '0'     :  calculates b with new character after removing first character of previous substring

Eg. Suppose string is 110101 and k = 3. We start with b = 0. Here size = 2^k = 8 and allOnes = 7 (111).
1. b = 000 << 1 = 000  ||  b &= (111) = 000(0)  ||  b |= 1  = 001         👉 b = 001 (1)
2. b = 001 << 1 = 010  ||  b &= (111) = 010(2)  ||  b |= 1  = 011         👉 b = 011 (3)
3. b = 011 << 1 = 110  ||  b &= (111) = 110(6)  ||  b |= 0  = 110         👉 b = 110 (6)
4. b = 110 << 1 = 1100 ||  b &= (111) = 100(4)  ||  b |= 1  = 101         👉 b = 101 (5)
            ^exceeds (2^k)   ^2nd step removes kth bit^
5. b = 101 << 1 = 1010 ||  b &= (111) = 010(2)  ||  b |= 0  = 010         👉 b = 010 (2)
6. b = 010 << 1 = 100  ||  b &= (111) = 100(4)  ||  b |= 1  = 101         👉 b = 101 (5)

We can observe that using rolling hash, after first k-1 steps, our hash value is of length k. This can be used for indexing the set which will store the distinct substrings of length k found. If number of distinct substrings is equal to size, then the string has all the binary combinations.