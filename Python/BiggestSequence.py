import random as r
def LongestIncreasingSubsequence(InitialList):
    SubSequences = [[],[InitialList[0]]]
    len0,len1 = 0,0
    for i in range(1,len(InitialList)):
        if InitialList[i] > SubSequences[1][len1 - 1]:
            SubSequences[1].append(InitialList[i])
            len1 += 1
        else:
            if len1 >= len0:
                SubSequences[0] = SubSequences[1]
                SubSequences[1] = [InitialList[i]]
                len0,len1 = len1,0
            else:
                SubSequences[1] = [InitialList[i]]
                len1 = 0
    if len0 > len1:
        return SubSequences[0]
    else:
        return SubSequences[1]     
InitialListMain = [r.randint(1,1000000000) for i in range(1000000)]
print(LongestIncreasingSubsequence(InitialListMain))