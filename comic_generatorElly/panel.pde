class Panel{
  float block_h = 100;
  float block_w = 100;
  ArrayList<Characters> cha;
  ArrayList<int[]> colorList = new ArrayList<int[]>();
  
  Panel(ArrayList<Characters> cha){
    colorList.add(new int[]{255, 0, 0});
    colorList.add(new int[]{0, 255, 0});
    colorList.add(new int[]{0, 0, 255});
    colorList.add(new int[]{255, 255, 0});
    colorList.add(new int[]{0, 255, 255});
    colorList.add(new int[]{0, 0, 0});
    this.cha = cha;
  }
  
  void display(){
    //1. draw canvas
    fill(255);
    noStroke();
    rect(0, 0, block_w*3, block_h*2);
    println(block_h*2);
    
    //2. draw all characters
    for(int i = 0; i < cha.size(); i++){
      if( cha.size()<=colorList.size() ){
        Characters c1 = cha.get(i);
        int[] color1 = colorList.get(i);
        fill(color1[0], color1[1], color1[2]);
        //which block the character is
        translate(block_w*c1.inBlock/2, block_h*c1.inBlock/2);
        ellipse(0,0,40,40);
        translate(-block_w*c1.inBlock/2, -block_h*c1.inBlock/2);
      }
    }
  }
}