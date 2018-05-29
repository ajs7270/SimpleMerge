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
	
	
	// compare �߾����� Ȯ��
	public boolean isMerged(){
		
		return true;
		
	}
	
	// ��ư ������ ��  -> edit ��� Ȱ��ȭ �ϰ� ���ߴ�
	
	public void MergeToLeft(){           // �������� ��ġ�� method
		
		if(isMerged())                   // Merge�� �����ϴٸ�
		{
			//System.out.println("Merge To Left button clicked");
			
			// ���õ� �κ��� ó������ ������ �ݺ���
			for(int i = sourceLineList.getFirst(); i <= sourceLineList.getLast(); i++ ){
				
				if(left_file.size() != i)               // left file�� �ҽ��ڵ� �� ���� right file�� �ٲٰ��� �ϴ� �ҽ��ڵ��� �� ���� üũ
				{
					if(left_file.get(i) == right_file.get(i))      // ������ line�� �ִ� ���
					{
						System.out.println("������ ���� ������ �����մϴ�.");
					}
					else
					{
						left_file.remove(i);
						left_file.add(i, right_file.get(i));
					}
				}
				
				else                         
				{
					left_file.add("\n");           // ���� �߰�
					left_file.remove(i);
					left_file.add(i, right_file.get(i));
				}
			}	

		}
		
	}
	
	public void MergeToRight(){           // ���������� ��ġ�� method
		
		if(isMerged())                    // Merge�� �����ϴٸ�
		{
			   //System.out.println("Merge To Right button clicked");
				
			   // ���õ� �κ��� ó������ ������ �ݺ���
		       for(int i = sourceLineList.getFirst(); i <= sourceLineList.getLast(); i++ ){
		    	   
		    	   if(right_file.size() != i)
		    	   {
		    		   if(right_file.get(i) == left_file.get(i))      // ������ line�� �ִ�  ���
						{
							System.out.println("������ ���� ������ �����մϴ�.");
						}
			    	   else
			    	   {
			    		   right_file.remove(i);
						   right_file.add(i, left_file.get(i));
			    	   }
		    	   }
		    	   else
		    	   {
		    		    right_file.add("\n");           // ���� �߰�
		    		    right_file.remove(i);
						right_file.add(i, left_file.get(i));
		    	   }
		    	   
				}	
			}
		}
}
