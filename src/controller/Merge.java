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
	
	
	// compare �߾����� Ȯ��
	public boolean isMerged(){
		
		return true;
		
	}
	
	// ��ư ������ ��  -> edit ��� Ȱ��ȭ �ϰ� ���ߴ�
	
	public void MergeToLeft(){           // �������� ��ġ�� method
		
		if(isMerged())                   // Merge�� �����ϴٸ�
		{
			//System.out.println("Merge To Left button clicked");
			
			// Integer iteration
			ListIterator<Integer> it = sourceLineList.listIterator();
			int index = 0;
			
			// ���õ� �κ��� ó������ ������ �ݺ���
			while(it.hasNext()){
				
				index = it.nextIndex();
				System.out.println(index);
								
				if(left_file.get(index) == right_file.get(index))      // ������ line�� �ִ� ���
				{
					System.out.println("������ ���� ������ �����մϴ�.");
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
	
	public void MergeToRight(){           // ���������� ��ġ�� method
		
		if(isMerged())                   // Merge�� �����ϴٸ�
		{
			//System.out.println("Merge To Left button clicked");
			
			// Integer iteration
			ListIterator<Integer> it = sourceLineList.listIterator();
			int index = 0;
			
			// ���õ� �κ��� ó������ ������ �ݺ���
			while(it.hasNext()){
				
				index = it.nextIndex();
				System.out.println(index);
								
				if(right_file.get(index) == left_file.get(index))      // ������ line�� �ִ� ���
				{
					System.out.println("������ ���� ������ �����մϴ�.");
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