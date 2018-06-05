import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

enum Status {
    EQUAL, CHANGE, ADD, DELETE
}

class Point {
	int x;
	int y;
}

public class compare {
	
	
	int[][] lcs_table;
	boolean isCompared;
	
	LinkedList<String> dataL;                  	// left data
	LinkedList<String> dataR;                 	// right data
	LinkedList<String> dataL_cp;                  // left data_copy
	LinkedList<String> dataR_cp;                 	// right data_copy
	LinkedList<Status> statusL;                  	// left data status
	LinkedList<Status> statusR;                 	// right data status
	LinkedList<Point> lcsList;
	
	public compare(LinkedList<String> L, LinkedList<String> R) {
	/* 파일 클래스 받아오면 수정
	public compare(File L, File R) {
		dataL = L.getData();
		dataR = R.getData();
		dataL_cp = L.getData();
		dataR_cp = R.getData();
	*/
		dataL = L;                  // left data
		dataR = R;                 // right data
		dataL_cp = L;                  // left data_copy
		dataR_cp = R;                 // right data_copy
		statusL = new LinkedList<Status>();
		statusR = new LinkedList<Status>();
		lcsList = new LinkedList<Point>();
		
		lcs();
		isCompared = true;
	}

	public void initStatus(Status L, Status R, int index){
		statusL.add(index, L);
		statusR.add(index, R);
	}
	
	public void setStatus(Status L, Status R, int index){
		statusL.set(index, L);
		statusR.set(index, R);
	}
	
	public LinkedList<Status> getStatus(String LR) {
		if(LR.equalsIgnoreCase("L")) {
			return statusL;
		}else if(LR.equalsIgnoreCase("R")) {
			return statusR;
		}else {
			return null;
		}		
	}
	
	public boolean isCompared() {
		return isCompared;
	}

	// 공통부분 길이
	public int LCS_length(int dataIndex) {
		return lcs_table[dataL_cp.get(dataIndex).length()][dataR_cp.get(dataIndex).length()];
	}
	
	public void lcs() {
		lcs_table = new int[dataL_cp.size()+1][dataR_cp.size()+1];
		int Llen = dataL_cp.size();
		int Rlen = dataR_cp.size();
		
		/* LCS 테이블 생성 */
		// 처음 라인 0 초기화
		for (int i = 0; i < Llen+1; i++) {
			for (int j = 0; j < Rlen+1; j++) {
				lcs_table[i][j]=0;
			}
		}
		// if 같으면 그때까지 공통부분+1 / else 다르면 둘 중에서 긴 공통부분 가져옴
		for (int i = 1; i < Llen+1; i++) {
			for (int j = 1; j < Rlen+1; j++) {
				if(dataL_cp.get(i-1).equals(dataR_cp.get(j-1))) {
					lcs_table[i][j] = lcs_table[i-1][j-1]+1;
					Point p = new Point();
					p.x = i;
					p.y = j;
					lcsList.add(p);
				} else {
					lcs_table[i][j] = Math.max(lcs_table[i][j-1], lcs_table[i-1][j]);
					if(lcs_table[i][j-1] < lcs_table[i-1][j]) {
					}
					else if(lcs_table[i][j-1] > lcs_table[i-1][j]) {
					}
				}
			}
		}
		
		// status를 길이만큼 모두 equal로 세팅
		int dataMaxLen = Math.max(dataL.size(), dataR.size());
		for(int i=0; i<dataMaxLen; i++) {
			initStatus(Status.EQUAL, Status.EQUAL, i);
		}
		
		// 라인 단위 추가나 삭제된 부분 추가
		int Ladd = 0;
		int Radd = 0;
		for(int i=0; i<lcsList.size()-1; i++) {
			if((lcsList.get(i+1).x - lcsList.get(i).x) != (lcsList.get(i+1).y - lcsList.get(i).y)) {
				if((lcsList.get(i+1).x - lcsList.get(i).x) != 1) {
					for(int k = lcsList.get(i).x; k < lcsList.get(i+1).x - 1; k++) {
						dataR.add(k+Ladd, "");
						setStatus(Status.ADD, Status.DELETE, k+Ladd);
						Radd++;
					}
				}
				if((lcsList.get(i+1).y - lcsList.get(i).y) != 1) {
					for(int k = lcsList.get(i).y; k < lcsList.get(i+1).y - 1; k++) {
						dataL.add(k+Radd, "");
						setStatus(Status.DELETE, Status.ADD, k+Radd);
						Ladd++;
					}
				}
			}
			else if((lcsList.get(i+1).y - lcsList.get(i).y) != 1){
				setStatus(Status.CHANGE, Status.CHANGE, lcsList.get(i).x);
			}
		}
		
		// 두 문서의 길이가 다르면 차이만큼 뒤에 공백라인 추가
		int gap = dataL.size() - dataR.size();
		if(gap>0) {	// 왼쪽이 더 길면 오른쪽 추가
			for(int i=0; i<gap; i++) {
				dataR.add("");
				initStatus(Status.ADD, Status.DELETE, statusL.size());
			}
		}else if(gap<0) {	// 오른쪽이 더 길면 왼쪽 추가
			for(int i=0; i>gap; i--) {
				dataL.add("");
				initStatus(Status.DELETE, Status.ADD, statusL.size());
			}
		}
	}
}
