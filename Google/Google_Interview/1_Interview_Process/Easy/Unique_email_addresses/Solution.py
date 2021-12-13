class Solution:
    def numUniqueEmails(self, emails: list[str]) -> int:
        '''
        local and domain name, split by @
        if . is in local, ignore
        but . in domain name is not ignore

        if + is in local, everything after the first + is ignore, not with domain

        input: list of emails
        output: number of unique email...

        First thought:
        keep track of a set to store unique email
        for each email from emailsList, split using @, then work with the local name, add back to the domain and store in set

        run time: N elements of length M => (M - 2) * N
        space: O(N * M)
        '''
        uniqueSet = set()
        for email in emails:
            local, domain = email.split("@")
            tempEmail = "@" + domain
            for char in local:
                if char == ".":
                    continue
                if char == "+":
                    break
                else:
                    tempEmail = char + tempEmail 
            uniqueSet.add(tempEmail)

        return len(uniqueSet)
        
if __name__ == "__main__":
    s = Solution()
    testcases = [
        [
            ["a@leetcode.com","b@leetcode.com","c@leetcode.com"],
            3
        ],
        [
            ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"],
            2
        ]

    ]
    for test in testcases:
        input, expected = test
        if s.numUniqueEmails(input) == expected:
            print("Passed for >>>", input)
        else:
            print("Failed >>>", input)