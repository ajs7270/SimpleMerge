import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class Merge {


	List<Integer> sourceLineList = new LinkedList<Integer>();
	List<String> left_file = new LinkedList<String>();
	List<String> right_file = new LinkedList<String>();
	
	Merge(List<Integer> sourceLine, File left, File right){
		
		sourceLineList = sourceLine;
		left_file = left.getData();
		right_file = right.getData();	
		
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
			
			// Integer iteration
			ListIterator<Integer> it = sourceLineList.listIterator();
			int index = 0;
			
			// 선택된 부분의 처음부터 끝까지 반복문
			while(it.hasNext()){
				
				index = it.nextIndex();
				System.out.println(index);
								
				if(left_file.get(index) == right_file.get(index))      // 동일한 line이 있는 경우
				{
					System.out.println("선택한 줄은 내용이 동일합니다.");
				}
				else
				{
					left_file.remove(index);
					left_file.add(index, right_file.get(index));
				}
				
				it.next();
			}	

		}
		
	}
	
	public void MergeToRight(){           // 오른쪽으로 합치는 method
		
		if(isMerged())                   // Merge가 가능하다면
		{
			//System.out.println("Merge To Left button clicked");
			
			// Integer iteration
			ListIterator<Integer> it = sourceLineList.listIterator();
			int index = 0;
			
			// 선택된 부분의 처음부터 끝까지 반복문
			while(it.hasNext()){
				
				index = it.nextIndex();
				System.out.println(index);
								
				if(right_file.get(index) == left_file.get(index))      // 동일한 line이 있는 경우
				{
					System.out.println("선택한 줄은 내용이 동일합니다.");
				}
				else
				{
					right_file.remove(index);
					right_file.add(index, left_file.get(index));
				}
				
				it.next();
			}	


		}
 }
}