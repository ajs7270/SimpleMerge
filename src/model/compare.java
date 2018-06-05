package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
	
	List<String> dataL = new LinkedList<String>();                  // left data
	List<String> dataR = new LinkedList<String>();                 	// right data
	List<Status> statusL;                  							// left data status
	List<Status> statusR;                 							// right data status
	List<Point> lcsList;
	
	public compare() {
		statusL = new LinkedList<Status>();
		statusR = new LinkedList<Status>();
		lcsList = new LinkedList<Point>();
		isCompared = false;
	}

	public void setFiles(List<String> L, List<String> R) {
		dataL = L;                  // left data
		dataR = R;                 	// right data
	}
	
	public void initStatus(Status L, Status R, int index){
		statusL.add(index, L);
		statusR.add(index, R);
	}
	
	public void setStatus(Status L, Status R, int index){
		statusL.set(index, L);
		statusR.set(index, R);
	}
	
	public List<Status> getStatus(String LR) {
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

	// 怨듯넻遺�遺� 湲몄씠
	public int LCS_length(int dataIndex) {
		return lcs_table[dataL.get(dataIndex).length()][dataR.get(dataIndex).length()];
	}
	
	public void lcs() {
		isCompared = true;
		lcs_table = new int[dataL.size()+1][dataR.size()+1];
		int Llen = dataL.size();
		int Rlen = dataR.size();
		
		/* LCS �뀒�씠釉� �깮�꽦 */
		// 泥섏쓬 �씪�씤 0 珥덇린�솕
		for (int i = 0; i < Llen+1; i++) {
			for (int j = 0; j < Rlen+1; j++) {
				lcs_table[i][j]=0;
			}
		}
		// if 媛숈쑝硫� 洹몃븣源뚯� 怨듯넻遺�遺�+1 / else �떎瑜대㈃ �몮 以묒뿉�꽌 湲� 怨듯넻遺�遺� 媛��졇�샂
		for (int i = 1; i < Llen+1; i++) {
			for (int j = 1; j < Rlen+1; j++) {
				if(dataL.get(i-1).equals(dataR.get(j-1))) {
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
		
		// status瑜� 湲몄씠留뚰겮 紐⑤몢 equal濡� �꽭�똿
		int dataMaxLen = Math.max(dataL.size(), dataR.size());
		for(int i=0; i<dataMaxLen; i++) {
			initStatus(Status.EQUAL, Status.EQUAL, i);
		}
		
		// �씪�씤 �떒�쐞 異붽��굹 �궘�젣�맂 遺�遺� 異붽�
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
		
		// �몢 臾몄꽌�쓽 湲몄씠媛� �떎瑜대㈃ 李⑥씠留뚰겮 �뮘�뿉 怨듬갚�씪�씤 異붽�
		int gap = dataL.size() - dataR.size();
		if(gap>0) {	// �쇊履쎌씠 �뜑 湲몃㈃ �삤瑜몄そ 異붽�
			for(int i=0; i<gap; i++) {
				dataR.add("");
				initStatus(Status.ADD, Status.DELETE, statusL.size());
			}
		}else if(gap<0) {	// �삤瑜몄そ�씠 �뜑 湲몃㈃ �쇊履� 異붽�
			for(int i=0; i>gap; i--) {
				dataL.add("");
				initStatus(Status.DELETE, Status.ADD, statusL.size());
			}
		}
	}
}
