import java.io.*;
import java.util.*;

class Node {
    int x, y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main1 {

    static int row, col;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        int cnt = 0;

        while ((cnt = seperateIce()) < 2) {
            if (cnt == 0) {
                ans = 0;
                break;
            }
            melt();
            ans++;
        }

        System.out.println(ans);

    }

    public static int seperateIce() {
        visited = new boolean[row][col];
        int cnt = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    // 현재 빙하가 어떻게 연결되어있는지 확인
    public static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
                if (!visited[nx][ny] && map[nx][ny] != 0) {
                    dfs(nx, ny);
                }
            }
        }
    }

    public static void melt() {
        Queue<Node> q = new LinkedList<>();

        boolean[][] v = new boolean[row][col];

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(map[i][j]!=0){
                    q.offer(new Node(i,j));
                    visited[i][j]=true;
                }
            }
        }

        while (!q.isEmpty()){
            Node curN = q.poll();

            int seaN = 0;

            for(int i=0;i<4;i++){
                int nx = curN.x + dx[i];
                int ny = curN.y + dy[i];

                if(nx>=0 && nx<row && ny>=0 && ny<col){
                    if(!visited[nx][ny] && map[nx][ny]==0){
                        seaN++;
                    }
                }
            }

            if(map[curN.x][curN.y]-seaN<0){
                map[curN.x][curN.y]=0;
            }else{
                map[curN.x][curN.y] -=seaN;
            }

        }
    }
}