package WordAnalysis;

import java.util.ArrayList;

public class WAArrayDistri {
	public ArrayList<String> getArithMeticOperator(ArrayList<String> arr){
		//算术运算符
		ArrayList<String> finstr = new ArrayList<String>();
		int start = arr.indexOf("$");
		int end = arr.indexOf("$$");
		for(int i=start+1;i<end;i++){
			finstr.add(arr.get(i));
		}
		return finstr;
	}

	public ArrayList<String> getRelationOperator(ArrayList<String> arr){
		//关系运算符
		ArrayList<String> finstr = new ArrayList<String>();
		int start = arr.indexOf("$$");
		int end = arr.indexOf("$$$");
		for(int i=start+1;i<end;i++){
			finstr.add(arr.get(i));
		}
		return finstr;
	}

	public ArrayList<String> getLogicOperator(ArrayList<String> arr){
		//逻辑运算符
		ArrayList<String> finstr = new ArrayList<String>();
		int start = arr.indexOf("$$$");
		int end = arr.indexOf("$$$$");
		for(int i=start+1;i<end;i++){
			finstr.add(arr.get(i));
		}
		return finstr;
	}
	public ArrayList<String> getBitOperator(ArrayList<String> arr){
		//位操作运算符
		ArrayList<String> finstr = new ArrayList<String>();
		int start = arr.indexOf("$$$$");
		int end = arr.indexOf("$$$$$");
		for(int i=start+1;i<end;i++){
			finstr.add(arr.get(i));
		}
		return finstr;
	}

	public ArrayList<String> getAssignOperator(ArrayList<String> arr){
		//赋值运算符
		ArrayList<String> finstr = new ArrayList<String>();
		int start = arr.indexOf("$$$$$");
		int end = arr.indexOf("$$$$$$");
		for(int i=start+1;i<end;i++){
			finstr.add(arr.get(i));
		}
		return finstr;
	}

	public ArrayList<String> getConditionOperator(ArrayList<String> arr){
		//条件运算符
		ArrayList<String> finstr = new ArrayList<String>();
		int start = arr.indexOf("$$$$$$");
		int end = arr.indexOf("$$$$$$$");
		for(int i=start+1;i<end;i++){
			finstr.add(arr.get(i));
		}
		return finstr;
	}



	public ArrayList<String> getBoundaryOperator(ArrayList<String> arr){
		//分隔符
		ArrayList<String> finstr = new ArrayList<String>();
		int start = arr.indexOf("$$$$$$$");
		int end = arr.indexOf("$$$$$$$$");
		for(int i=start+1;i<end;i++){
			finstr.add(arr.get(i));
		}
		return finstr;
	}

	public ArrayList<String> getRemarkOperator(ArrayList<String> arr){
		//记号符
		ArrayList<String> finstr = new ArrayList<String>();
		int start = arr.indexOf("$$$$$$$$");
		int end = arr.size();
		for(int i=start+1;i<end;i++){
			finstr.add(arr.get(i));
		}
		return finstr;
	}

	public String[][] getStr(int column,ArrayList<String> arr){
		int arr_length = arr.size(),start=0;
		String[][] str = new String[arr_length][column];


		for(int i = 0;i<arr_length;i++){
			for(int j=0;j<column;j++){
				if(start%2==1){
					str[i][j] = arr.get(start/2);
				}else{
					int temp = start/2+1;
					str[i][j] =temp +"";
				}
				start++;
			}
		}
		return str;
	}

	public String[][] getWAResult(ArrayList<String> arr,int column){
		int str_length = arr.size()/column;
		String [][] str = new String[str_length][column];
		for(int i=0;i<str_length;i++){
			for(int j=0;j<column;j++){
				str[i][j] = arr.get(i*column+j);
			}
		}

		return str;
	}

	private String[][] getSeparator(int column,ArrayList<String> arr,ArrayList<String> s_arr){
		int arr_length = arr.size(),start=0;
		String[][] str = new String[arr_length][column];


		for(int i = 0;i<arr_length;i++){
			for(int j=0;j<column;j++){
				if(start%2==1){
					str[i][j] = arr.get(start/2);
				}else{
					int temp = start/2;
					temp = s_arr.indexOf(arr.get(temp))+1;
					str[i][j] =temp +"";
				}
				start++;
			}
		}
		return str;
	}


	public String[][] getStr(int column,ArrayList<String> arr,String str){
		if(str.equals("算术运算符")){
			ArrayList<String> temparr =getArithMeticOperator(arr);
			return getSeparator(column,temparr,arr);
		}else if(str.equals("关系运算符")){
			ArrayList<String> temparr =getRelationOperator(arr);
			return getSeparator(column,temparr,arr);
		}else if(str.equals("逻辑运算符")){
			ArrayList<String> temparr =getLogicOperator(arr);
			return getSeparator(column,temparr,arr);
		}else if(str.equals("位操作运算符")){
			ArrayList<String> temparr =getBitOperator(arr);
			return getSeparator(column,temparr,arr);
		}else if(str.equals("赋值运算符")){
			ArrayList<String> temparr =getAssignOperator(arr);
			return getSeparator(column,temparr,arr);
		}else if(str.equals("条件运算符")){
			ArrayList<String> temparr =getConditionOperator(arr);
			return getSeparator(column,temparr,arr);
		}else if(str.equals("分隔符")){
			ArrayList<String> temparr =getBoundaryOperator(arr);
			return getSeparator(column,temparr,arr);
		}else if(str.equals("记号符")){
			ArrayList<String> temparr =getRemarkOperator(arr);
			return getSeparator(column,temparr,arr);
		}else{
			return null;
		}

	}
}
