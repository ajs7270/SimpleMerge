package model;

import java.util.LinkedList;
import java.util.List;

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
		isCompared = false;
	}
	
	/* init data for compare */
	public void setFiles(List<String> L, List<String> R) {
		dataL = L;                  // left data
		dataR = R;                 	// right data

		statusL = new LinkedList<Status>();
		statusR = new LinkedList<Status>();
		lcsList = new LinkedList<Point>();
	}
	
	/* SetStatus 1) alloc(): for new memory allocate 	2) replace(): replace origin data */
	interface SetStatus {
		void setStatus(Status L, Status R, int index);
	}
	
	class alloc implements SetStatus {
		public void setStatus(Status L, Status R, int index) {
			statusL.add(index, L);
			statusR.add(index, R);
		}
	}
	class replace implements SetStatus {
		public void setStatus(Status L, Status R, int index) {
			statusL.set(index, L);
			statusR.set(index, R);
		}
	}
	
	/* return left/right data status list */
	public List<Status> getStatus(String LR) {
		if(LR.equalsIgnoreCase("L")) {
			return statusL;
		}else if(LR.equalsIgnoreCase("R")) {
			return statusR;
		}else {
			return null;
		}		
	}
	
	/* check is compare class executed */
	public boolean isCompared() {
		return isCompared;
	}

	/* return LCS length */
	public int LCS_length() {
		return lcs_table[dataL.size()][dataR.size()];
	}
	
	/* longest common subsequence */
	public void lcs() {

		int dataMaxLen = Math.max(dataL.size(), dataR.size());			// max length of both data
		
		// check empty file
		if (dataMaxLen != 0) {
			isCompared = true;
			lcs_table = new int[dataL.size()+1][dataR.size()+1];
			int Llen = dataL.size();
			int Rlen = dataR.size();
			
			/* create LCS table */
			// initialize LCS table as 0
			for (int i = 0; i < Llen+1; i++) {
				for (int j = 0; j < Rlen+1; j++) {
					lcs_table[i][j]=0;
				}
			}
			
			// new lcs_table[i][j]
			// if same str: lcs_table[i-1][j-1]+1 
			// else different str: max of lcs_table[i-1][j], lcs_table[i][j-1]
			for (int i = 1; i < Llen+1; i++) {
				for (int j = 1; j < Rlen+1; j++) {
					if(dataL.get(i-1).replaceAll("\\s+$", "").equals(dataR.get(j-1).replaceAll("\\s+$", ""))) {
						lcs_table[i][j] = lcs_table[i-1][j-1]+1;
					} else {
						lcs_table[i][j] = Math.max(lcs_table[i][j-1], lcs_table[i-1][j]);
					}
				}
			}
			
			/* create lcs list: positioning for finding status*/
			if (lcs_table[1][1] == 1) {
				for (int i = 1; i < Llen+1; i++) {
					for (int j = 1; j < Rlen; j++) {
						if(dataL.get(i-1).replaceAll("\\s+$", "").equals(dataR.get(j-1).replaceAll("\\s+$", ""))) {
							if(lcsList.isEmpty() || j > lcsList.get(lcsList.size()-1).y) {
								Point p = new Point();
								p.x = i;
								p.y = j;
								lcsList.add(p);
								break;
							}
						}
					}
				}
			} else {
				for (int j = 1; j < Rlen+1; j++) {
					for (int i = 1; i < Llen; i++) {
						if(dataL.get(i-1).replaceAll("\\s+$", "").equals(dataR.get(j-1).replaceAll("\\s+$", ""))) {
							if(lcsList.isEmpty() || i > lcsList.get(lcsList.size()-1).x) {
								Point p = new Point();
								p.x = i;
								p.y = j;
								lcsList.add(p);
								break;
							}
						}
					}
				}
			}
			
			// include remaining strings to lcs list
			int lcs_len = LCS_length();
			Point lastP = new Point();
			lastP.x = lcsList.get(lcsList.size()-1).x;
			lastP.y = lcsList.get(lcsList.size()-1).y;
			for (int i = 1; i < Llen+1; i++) {
				for (int j = 1; j < Rlen+1; j++) {
					if(lcs_table[i][j] == lcs_len) {
						Point p = new Point();
						p.x = i;
						p.y = j;
						if(!(p.x == lastP.x && p.y == lastP.y)) {
							lcsList.add(p);
						}
						break;
					}
				}
			}
			
			/* create data status list with lcs table & lcs list */
			
			SetStatus ss = new alloc();						// SetStatus mode change to alloc
			// init status list as EQUAL
			for(int i=0; i<dataMaxLen; i++) {
				ss.setStatus(Status.EQUAL, Status.EQUAL, i);
			}	
			ss = new replace();								// SetStatus mode change to replace
			
			// set status list [EQUAL ADD DELETE CHANGE]
			int Ladd = 0;	// check added string lines_L
			int Radd = 0;	// check added string lines_R
			
			// if start is empty lines
			if(lcsList.get(0).x != 1) {
				for(int i=0; i<lcsList.get(0).x - 1; i++) {
					if(statusL.size() == dataMaxLen)	ss = new alloc();
					dataR.add(0+Ladd, "");
					ss.setStatus(Status.ADD, Status.DELETE, i+Ladd);
					Radd++;			
				}
				ss.setStatus(Status.EQUAL, Status.EQUAL, lcsList.get(0).x-1+Radd);			
			}
			
			// main status calc alg.
			for(int i=0; i<lcsList.size()-1; i++) {
				if(statusL.size() == dataMaxLen)	ss = new alloc();	// check if dataMaxLen is overflowed
				int xgap = lcsList.get(i+1).x - lcsList.get(i).x;		// lcs list position xgap
				int ygap = lcsList.get(i+1).y - lcsList.get(i).y;		// lcs list position xgap
				
				// number of lines are different
				if(xgap != ygap) {
					
					if(ygap==0) {
						if(lcs_table[lcsList.get(i).x][lcsList.get(i).y] == lcs_len) {
							dataR.add(lcsList.get(i).x+Ladd, "");
							ss.setStatus(Status.ADD, Status.DELETE, lcsList.get(i).x+Ladd);
							Radd++;
						}
						else {
							ss.setStatus(Status.ADD, Status.DELETE, lcsList.get(i).x+Ladd);
						}
							
					}
					else if(xgap==0) {
						if(lcs_table[lcsList.get(i).x][lcsList.get(i).y] == lcs_len) {
							dataR.add(lcsList.get(i).y+Radd, "");
							ss.setStatus(Status.DELETE, Status.ADD, lcsList.get(i).y+Radd);
							Ladd++;
						}
						else {
							ss.setStatus(Status.DELETE, Status.ADD, lcsList.get(i).y+Radd);	
						}
					}
					
					// xgap nor ygap neither is 0
					else {
						// xgap > 1 && ygap > 1
						if(ygap != 1 && xgap != 1) {
							if(xgap > ygap) {
								for(int n=0; n<xgap-ygap; n++) {
									if(statusL.size() == dataMaxLen)	ss = new alloc();
									if(dataL.get(lcsList.get(i).x+ygap+n+Ladd).equals("")) {
										ss.setStatus(Status.DELETE, Status.ADD, lcsList.get(i).x+n+Ladd);
									} else if(dataR.get(lcsList.get(i).x+ygap+n+Ladd).equals("")) {
										ss.setStatus(Status.ADD, Status.DELETE, lcsList.get(i).x+n+Ladd);
									} else {
										ss.setStatus(Status.CHANGE, Status.CHANGE, lcsList.get(i).x+n+Ladd);
									}
								}
								for(int m=0; m<xgap-ygap; m++) {
									if(statusL.size() == dataMaxLen)	ss = new alloc();
									dataR.add(lcsList.get(i).x+Ladd, "");
									ss.setStatus(Status.ADD, Status.DELETE, lcsList.get(i).x+m+Ladd);
									Radd++;
								}
							
							}
							else if(xgap < ygap) {
								for(int n=0; n<ygap-xgap; n++) {
									if(statusL.size() == dataMaxLen)	ss = new alloc();
									if(dataL.get(lcsList.get(i).y+xgap+n+Radd).equals("")) {
										ss.setStatus(Status.DELETE, Status.ADD, lcsList.get(i).y+n+Radd);
									} else if(dataR.get(lcsList.get(i).y+xgap+n+Radd).equals("")) {
										ss.setStatus(Status.ADD, Status.DELETE, lcsList.get(i).y+n+Radd);
									} else {
										ss.setStatus(Status.CHANGE, Status.CHANGE, lcsList.get(i).y+n+Radd);
									}
								}
								for(int m=0; m<xgap-ygap; m++) {
									if(statusL.size() == dataMaxLen)	ss = new alloc();
									dataL.add(lcsList.get(i).y+Radd, "");
									ss.setStatus(Status.ADD, Status.DELETE, lcsList.get(i).y+m+Radd);
									Ladd++;
								}
							}
						}
						// dataL is deleted -> add empty lines at dataL
						else if(xgap == 1) {
							for(int k = lcsList.get(i).y; k < lcsList.get(i+1).y - 1; k++) {
								if(statusL.size() == dataMaxLen)	ss = new alloc();
								dataL.add(k+Radd, "");
								ss.setStatus(Status.DELETE, Status.ADD, k+Radd);
								Ladd++;
							}
						}
						
						// dataR is deleted -> add empty lines at dataR
						else if(ygap == 1){
							for(int k = lcsList.get(i).x; k < lcsList.get(i+1).x - 1; k++) {
								if(statusL.size() == dataMaxLen)	ss = new alloc();
								dataR.add(k+Ladd, "");
								ss.setStatus(Status.ADD, Status.DELETE, k+Ladd);
								Radd++;
							}
						}
					}
				}
				
				// number of lines are equal
				// distinguish if empty line is contained -> added/deleted
				else if((lcsList.get(i+1).y - lcsList.get(i).y) != 1){
					for(int k = lcsList.get(i).y; k < lcsList.get(i+1).y - 1; k++) {
						if(statusL.size() == dataMaxLen)	ss = new alloc();
						if(dataL.get(k+Radd).equals("")) {
							ss.setStatus(Status.DELETE, Status.ADD, k+Radd);
						} else if(dataR.get(k+Radd).equals("")) {
							ss.setStatus(Status.ADD, Status.DELETE, k+Radd);
						} else {
							ss.setStatus(Status.CHANGE, Status.CHANGE, k+Radd);
						}
					}	
				}
				
				// EQUAL case
				else {}
				
			}
			
			// add empty line by data length difference
			int gap = dataL.size() - dataR.size();
			if(gap>0) {	// left>right -> right add 
				for(int i=0; i<gap; i++) {
					dataR.add("");
					ss.setStatus(Status.ADD, Status.DELETE, statusL.size());
				}
			}else if(gap<0) {	// right<left -> left add
				for(int i=0; i>gap; i--) {
					dataL.add("");
					ss.setStatus(Status.DELETE, Status.ADD, statusL.size());
				}
			}
		}
	}
}
