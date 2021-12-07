class Solution:
    def getHint(self, secret: str, guess: str) -> str:
        # expect xAyB where x, y are variables and A, B is just symbol
        '''
        Keep track of the digit in the secret string
        secrectDict = {
            "1": {0, 2},
            "2": {3},
            "4": {1}
        }
        guessDict = {
            "1": 
        }
        Go through the guess, if the element is in the dictionary, increase either bulls or cows based on the element

        Need to make sure that the frequency of char in the guess is not over the frequency in secret

        secret: 1123 and guess: 0111
        with 1 cows and 1 bulls

        first traverse and check all the position where bulls happen
        then apply the method we discuss on the second traversal to check the cows

        '''
        bulls = 0
        cows = 0
        bullsPosition = set()
        for i in range(len(secret)):
            if secret[i] == guess[i]:
                bullsPosition.add(i)
                bulls += 1
        if len(secret) > len(bullsPosition):
            secretDict = dict()
            for i in range(len(secret)):
                if i not in bullsPosition:
                    if secret[i] not in secretDict:
                        secretDict[secret[i]] = 0
                    secretDict[secret[i]] += 1
            

            for i in range(len(guess)):
                if i not in bullsPosition:
                    char = guess[i]
                    if char in secretDict and secretDict[char] > 0:
                        cows += 1
                        secretDict[char] -= 1
        
        return "{}A{}B".format(bulls, cows)

s = Solution()
testcases = [
    # ["2311", "1110", "1A1B"],
    # ["1807", "7810", "1A3B"],
    # ["1", "0", "0A0B"],
    # ["1", "1", "1A0B"],
    ["11", "10", "1A0B"]
]
for test in testcases:
    secret = test[0]
    guess = test[1]
    expectHint = test[2]
    if s.getHint(secret, guess) == expectHint:
        print("Passed >>>", test)
    else:
        print("Failed >>>", test)
