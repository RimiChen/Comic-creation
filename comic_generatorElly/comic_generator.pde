void setup(){
  size(800,600);
  generateComic();
}

void draw(){

}


void generateComic(){
  //initial everything, the following code is used to test
  Structure structre = new Structure();
  ArrayList<Characters> chara = new ArrayList<Characters>();
  chara.add(new Characters(1));
  ArrayList<Panel> panel = new ArrayList<Panel>();
  panel.add(new Panel(chara));
  
  //draw all panel, the following code is used to test
  Panel pt = panel.get(0);
  pt.display();
}