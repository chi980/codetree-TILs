# [1차원 폭발 게임 ](https://www.codetree.ai/missions/2/problems/The-1D-bomb-game)

|유형|문제 경험치|난이도|
|---|---|---|
|[Intermediate Low / Simulation / 격자 안에서 터지고 떨어지는 경우](https://www.codetree.ai/missions?missionId=2)|80xp|![보통][medium]|


stack을 사용했는데 pointer로 푸는 게 나을 듯
### 1. Simulation
```
import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 100;
    
    public static int n, m, endOfArray, endOfTemp;
    public static int[] numbers = new int[MAX_NUM];
    public static int[] temp = new int[MAX_NUM];
    
    // 주어진 시작점에 대하여 
    // 부분 수열의 끝 위치를 반환합니다.
    public static int getEndIdxOfExplosion(int startIdx, int currNum) {
        int endIdx = startIdx + 1;
        while(endIdx < endOfArray) {
            if(numbers[endIdx] == currNum)
                endIdx++;
            else{
                break;
            }
        }
    
        return endIdx - 1;
    }
    
    // 터져야 할 폭탄들에 대해 터졌다는 의미로 0을 채워줍니다.
    public static void fillZero(int startIdx, int endIdx) {
        for(int i = startIdx; i <= endIdx; i++) {
            numbers[i] = 0;
        }
    }
    
    // Arr에서 폭탄이 터진 이후의 결과를 Temp에 임시로 저장합니다. 
    public static void moveToTemp() {
        endOfTemp = 0;
        for(int i = 0; i < endOfArray; i++) {
            if(numbers[i] != 0) {
                temp[endOfTemp++] = numbers[i];
            }
        }
    }
    
    // Temp배열을 그대로 Copy하여 Arr에 저장합니다.
    public static void copy() {
        endOfArray = endOfTemp;
        for(int i = 0; i < endOfArray; i++) {
            numbers[i] = temp[i];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 0; i < n; i++)
            numbers[i] = sc.nextInt();
        endOfArray = n;

        boolean didExplode;
        do {
            didExplode = false;
            for(int currIdx = 0; currIdx < endOfArray; currIdx++) {  
                // 각 위치마다 그 뒤로 폭탄이 m개 이상 있는지 확인합니다.
                
                // 이미 터지기로 예정되어있는 폭탄은 패스합니다.
                if(numbers[currIdx] == 0) { 
                    continue;
                }
                // currIdx로부터 연속하여 같은 숫자를 갖는 폭탄 중 
                // 가장 마지막 위치를 찾아 반환합니다.
                int endIdx = getEndIdxOfExplosion(currIdx, numbers[currIdx]);

                if(endIdx - currIdx + 1 >=  m) {
                    // 연속한 숫자의 개수가 m개 이상인 경우 폭탄이 터졌음을 기록해줍니다.
                    fillZero(currIdx, endIdx);
                    didExplode = true;
                }
            }
            
            // Arr에서 폭탄이 터진 이후의 결과를 Temp에 임시로 저장합니다. 
            moveToTemp();
            // Temp배열을 그대로 Copy하여 Arr에 저장합니다.
            copy();       
        }
        while(didExplode); // 더 이상 폭탄이 터질 수 없을 때까지 반복합니다.

        System.out.println(endOfArray);
        for(int i = 0; i < endOfArray; i++)
            System.out.println(numbers[i]);
    }
}
```
T: O( N ^ 2 )
M: O( N )
### 2. Simulation + MemoryOptimization
```
import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 100;
    
    public static int n, m, endOfArray;
    public static int[] numbers = new int[MAX_NUM];
    
    public static int getEndIdxOfExplosion(int startIdx, int currNum) {
        int endIdx = startIdx + 1;
        while(endIdx < endOfArray) {
            if(numbers[endIdx] == currNum)
                endIdx++;
            else{
                break;
            }
        }
    
        return endIdx - 1;
    }
    
    // 입력 배열에서 지우고자 하는 부분 수열을 삭제합니다.
    public static void cutArray(int startIdx, int endIdx) {
        int cutLen = endIdx - startIdx + 1;
        for(int i = endIdx + 1; i < endOfArray; i++) {
            numbers[i - cutLen] = numbers[i];
        }
        
        endOfArray -= cutLen;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 0; i < n; i++)
            numbers[i] = sc.nextInt();
        endOfArray = n;

        boolean didExplode;
        int currIdx;
        do {
            didExplode = false;
            currIdx = 0;

            while(currIdx < endOfArray) {
                int endIdx = getEndIdxOfExplosion(currIdx, numbers[currIdx]);

                if(endIdx - currIdx + 1 >=  m) {
                    // 연속한 숫자의 개수가 m개 이상이면
                    // 폭탄이 터질 수 있는 경우 해당 부분 수열을 잘라내고
                    // 폭탄이 터졌음을 기록해줍니다.
                    cutArray(currIdx, endIdx);
                    didExplode = true;
                }
                else {
                    // 주어진 시작 원소에 대하여 폭탄이 터질 수 없는 경우
                    // 다음 원소에 대하여 탐색하여 줍니다.
                    currIdx++;
                }
            }
        }
        while(didExplode);

        System.out.println(endOfArray);
        for(int i = 0; i < endOfArray; i++)
            System.out.println(numbers[i]);
    }
}
```
T: O( N ^ 2 )
M: O( N )

### 3.  Simulation + MemoryOptimization + TimeOptimization
```
import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 100;
    
    public static int n, m, endOfArray;
    public static int[] numbers = new int[MAX_NUM];
    
    public static int getEndIdxOfExplosion(int startIdx, int currNum) {
        int endIdx = startIdx + 1;
        while(endIdx < endOfArray) {
            if(numbers[endIdx] == currNum)
                endIdx++;
            else{
                break;
            }
        }
    
        return endIdx - 1;
    }
    
    public static void cutArray(int startIdx, int endIdx) {
        int cutLen = endIdx - startIdx + 1;
        for(int i = endIdx + 1; i < endOfArray; i++) {
            numbers[i - cutLen] = numbers[i];
        }
        
        endOfArray -= cutLen;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 0; i < n; i++)
            numbers[i] = sc.nextInt();
        endOfArray = n;

        boolean didExplode;
        int currIdx;
        do {
            didExplode = false;
            currIdx = 0;

            while(currIdx < endOfArray) {
                int endIdx = getEndIdxOfExplosion(currIdx, numbers[currIdx]);

                if(endIdx - currIdx + 1 >=  m) {
                    // 연속한 숫자의 개수가 m개 이상이면
                    // 폭탄이 터질 수 있는 경우 해당 부분 수열을 잘라내고
                    // 폭탄이 터졌음을 기록해줍니다.
                    cutArray(currIdx, endIdx);
                    didExplode = true;
                }
                else {
                    // 폭탄이 터질 수 없는 경우 
                    // 순회할 필요가 없는 원소에 대한 탐색을 생략해줍니다.
                    currIdx = endIdx + 1;
                }
            }
        }
        while(didExplode); 

        System.out.println(endOfArray);
        for(int i = 0; i < endOfArray; i++)
            System.out.println(numbers[i]);
    }
}
```
T: O( N ^ 2 / M )
M: O( N )


[b5]: https://img.shields.io/badge/Bronze_5-%235D3E31.svg
[b4]: https://img.shields.io/badge/Bronze_4-%235D3E31.svg
[b3]: https://img.shields.io/badge/Bronze_3-%235D3E31.svg
[b2]: https://img.shields.io/badge/Bronze_2-%235D3E31.svg
[b1]: https://img.shields.io/badge/Bronze_1-%235D3E31.svg
[s5]: https://img.shields.io/badge/Silver_5-%23394960.svg
[s4]: https://img.shields.io/badge/Silver_4-%23394960.svg
[s3]: https://img.shields.io/badge/Silver_3-%23394960.svg
[s2]: https://img.shields.io/badge/Silver_2-%23394960.svg
[s1]: https://img.shields.io/badge/Silver_1-%23394960.svg
[g5]: https://img.shields.io/badge/Gold_5-%23FFC433.svg
[g4]: https://img.shields.io/badge/Gold_4-%23FFC433.svg
[g3]: https://img.shields.io/badge/Gold_3-%23FFC433.svg
[g2]: https://img.shields.io/badge/Gold_2-%23FFC433.svg
[g1]: https://img.shields.io/badge/Gold_1-%23FFC433.svg
[p5]: https://img.shields.io/badge/Platinum_5-%2376DDD8.svg
[p4]: https://img.shields.io/badge/Platinum_4-%2376DDD8.svg
[p3]: https://img.shields.io/badge/Platinum_3-%2376DDD8.svg
[p2]: https://img.shields.io/badge/Platinum_2-%2376DDD8.svg
[p1]: https://img.shields.io/badge/Platinum_1-%2376DDD8.svg
[passed]: https://img.shields.io/badge/Passed-%23009D27.svg
[failed]: https://img.shields.io/badge/Failed-%23D24D57.svg
[easy]: https://img.shields.io/badge/쉬움-%235cb85c.svg?for-the-badge
[medium]: https://img.shields.io/badge/보통-%23FFC433.svg?for-the-badge
[hard]: https://img.shields.io/badge/어려움-%23D24D57.svg?for-the-badge
