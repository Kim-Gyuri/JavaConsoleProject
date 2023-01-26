package console.ghostLeg.domain;

public class GhostLegField {
    private String[][] field;
    private String[] thingsOfBottom;
    GhostLegGenerator ghostLegGenerator;


    public GhostLegField(int size, String things) {
        this.ghostLegGenerator = new GhostLegGenerator();
        this.field = ghostLegGenerator.createField(size);
        this.thingsOfBottom = ghostLegGenerator.setThingOfBottom(things);
    }

    public String[][] getField() {
        return field;
    }

    public String[] getThingsOfBottom() {
        return thingsOfBottom;
    }

    public void printField(PlayerGroup players) {
        visibleField(players);
    }

    private void visibleField(PlayerGroup players) {
        // 플레이어 이름 표시
        System.out.println(String.join("      ", players.getPlayerNameList()));

        //사다리
        for (String[] line : field) {
            System.out.println(String.join("", line));
        }

        //사다리 맨 아래로 도달했을 때 나올 값을 표시
        System.out.println(String.join("      ", thingsOfBottom));
    }


}
