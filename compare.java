import java.util.ArrayList;
import java.util.Arrays;

enum Status {
    EQUAL, DIFF, ADD, DELETE
}

public class compare {
	
	private ArrayList<String> dataL;
	private ArrayList<String> dataR;
	private int[][] lcs_table;
	private ArrayList<Status> statusL;
	private ArrayList<Status> statusR;
	private boolean isCompared;
	
	public compare(ArrayList<String> L, ArrayList<String> R) {
		this.dataL = L;
		this.dataR = R;
		statusL = new ArrayList<Status>();
		statusR = new ArrayList<Status>();
		lcs();
		System.out.println(statusL);
		System.out.println(statusR);
		isCompared = true;
	}
	
	public void setStatus(Status L, Status R){
		statusL.add(L);
		statusR.add(R);
	}
	
	public ArrayList<Status> getStatus(String LR) {
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
	public void lcs() {
		int dataLen = (dataL.size() > dataR.size()) ? dataL.size() : dataR.size();
		
		for(int dataIndex=0; dataIndex<dataLen; dataIndex++) {
			
			lcs_table = new int[dataL.get(dataIndex).length()+1][dataR.get(dataIndex).length()+1];
			
			// LCS 테이블 생성
			for (int i = 0; i < dataL.get(dataIndex).length()+1; i++) {
				for (int j = 0; j < dataR.get(dataIndex).length()+1; j++) {
					lcs_table[i][j]=0;
				}
			}	
			for (int i = 1; i < dataL.get(dataIndex).length()+1; i++) {
				for (int j = 1; j < dataR.get(dataIndex).length()+1; j++) {
					if(dataL.get(dataIndex).charAt(i-1) == dataR.get(dataIndex).charAt(j-1)) {
						lcs_table[i][j] = lcs_table[i-1][j-1]+1;
					} else {
						lcs_table[i][j] = Math.max(lcs_table[i][j-1], lcs_table[i-1][j]);
					}
				}
			}
			for (int i = 0; i < dataL.get(dataIndex).length()+1; i++) {
				for (int j = 0; j < dataR.get(dataIndex).length()+1; j++) {
					System.out.print(lcs_table[i][j]);
				}
				System.out.println();
			}	
			
			// line by line 상태 비교
			if ((LCS_length(dataIndex) == dataL.get(dataIndex).length()) && (LCS_length(dataIndex) == dataR.get(dataIndex).length())) {	//공통부분길이가 L,R 전체 길이와 동일하면 equal
				setStatus(Status.EQUAL, Status.EQUAL);
			}
			
			else if(LCS_length(dataIndex) == 0) {							// 공통부분길이가 0일때
				if(dataL.get(dataIndex).length() == 0) {		// 왼쪽 문장 길이도 0이면 오른쪽 문장 추가된 것 
					setStatus(Status.DELETE, Status.ADD);	
				} else if(dataR.get(dataIndex).length() == 0) {	// 오른쪽 문장 길이도 0이면 왼쪽 문장 추가된 것
					setStatus(Status.ADD, Status.DELETE);	
				} 
			}
			else {												// 다 해당되지 않으면 그냥 일부만 다른 문장
				setStatus(Status.DIFF, Status.DIFF);	
			}
			
			// 두 문서의 길이가 다르면 차이만큼 공백라인 추가
			int gap = dataL.get(dataIndex).length() - dataR.get(dataIndex).length();
			if(gap>0) {	// 왼쪽이 더 길면 오른쪽 추가
				for(int i=0; i<gap; i++) {
					dataR.add("");
				}
			}else if(gap<0) {	// 오른쪽이 더 길면 왼쪽 추가
				for(int i=0; i>gap; i--) {
					dataL.add("");
				}
			}
			
		}
	}
	
	// 공통부분 길이
	public int LCS_length(int dataIndex) {
		return lcs_table[dataL.get(dataIndex).length()][dataR.get(dataIndex).length()];
	}
}
