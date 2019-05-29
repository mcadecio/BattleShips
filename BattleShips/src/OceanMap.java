public class OceanMap {

	private int row = 10, col = 10;
	private char[][] map;
	
	public void initialise(){
		map = new char[row][col];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = ' ';
			}
		}
	}
	public void printMap(){
		System.out.println();
		System.out.println("   0123456789   ");
		for (int i = 0; i < map.length; i++) {
			System.out.printf("%d |", i);
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == '2' || map[i][j] == '*'){
					System.out.print(" ");
				}
				else{
					System.out.print(map[i][j]);
				}
			}
			System.out.printf("| %d\n", i);
			
			
		}
		System.out.println("   0123456789   ");
		System.out.println();
	}
	public boolean setPos(int row, int col, char value){
		if (!isValid(row, col, value)){
			return false;
		}
		else
			map[row][col] = value;
		return true;
	}
	private boolean isValid(int x, int y, char value) {
		// TODO Auto-generated method stub
		try{
		if(map[x][y] == ' '){
			return true;
		}else
			return false;
		}catch (Exception e){
			return false;
		}
	}
	public char attackMove(int x, int y){
		return map[x][y];
	}
	public void mark(int x, int y, char v){
		map[x][y] = v;
	}
	
}
