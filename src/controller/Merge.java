import java.util.LinkedList;

public class Merge {


	LinkedList<Integer> sourceLineList = new LinkedList<Integer>();
	LinkedList<String> left_file = new LinkedList<String>();
	LinkedList<String> right_file = new LinkedList<String>();
	
	Merge(LinkedList<Integer> sourceLine, File left, File right){
		
		sourceLineList = sourceLine;
		left_file = left.get();
		right_file = right.get();	
		
	}
	
	
	// compare 했었는지 확인
	public boolean isMerged(){
		
		return true;
		
	}
	
	// 버튼 눌렀을 때  -> edit 모드 활성화 하고 꺼야댐
	
	public void MergeToLeft(){           // 왼쪽으로 합치는 method
		
		if(isMerged())                   // Merge가 가능하다면
		{
			//System.out.println("Merge To Left button clicked");
			
			// 선택된 부분의 처음부터 끝까지 반복문
			for(int i = sourceLineList.getFirst(); i <= sourceLineList.getLast(); i++ ){
				
				if(left_file.size() != i)               // left file의 소스코드 줄 수와 right file의 바꾸고자 하는 소스코드의 줄 순서 체크
				{
					if(left_file.get(i) == right_file.get(i))      // 동일한 line이 있는 경우
					{
						System.out.println("선택한 줄은 내용이 동일합니다.");
					}
					else
					{
						left_file.remove(i);
						left_file.add(i, right_file.get(i));
					}
				}
				
				else                         
				{
					left_file.add("\n");           // 공백 추가
					left_file.remove(i);
					left_file.add(i, right_file.get(i));
				}
			}	

		}
		
	}
	
	public void MergeToRight(){           // 오른쪽으로 합치는 method
		
		if(isMerged())                    // Merge가 가능하다면
		{
			   //System.out.println("Merge To Right button clicked");
				
			   // 선택된 부분의 처음부터 끝까지 반복문
		       for(int i = sourceLineList.getFirst(); i <= sourceLineList.getLast(); i++ ){
		    	   
		    	   if(right_file.size() != i)
		    	   {
		    		   if(right_file.get(i) == left_file.get(i))      // 동일한 line이 있는  경우
						{
							System.out.println("선택한 줄은 내용이 동일합니다.");
						}
			    	   else
			    	   {
			    		   right_file.remove(i);
						   right_file.add(i, left_file.get(i));
			    	   }
		    	   }
		    	   else
		    	   {
		    		    right_file.add("\n");           // 공백 추가
		    		    right_file.remove(i);
						right_file.add(i, left_file.get(i));
		    	   }
		    	   
				}	
			}
		}
}
