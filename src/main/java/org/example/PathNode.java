package org.example;

public class PathNode extends Point {

    public Point goal;
    public Point position;
    public int LengthFromStart;
    public PathNode CameFrome;//родительская точка
    public int LengthToGoal;
    public int FullLength;

    public PathNode(Point point, PathNode pathNode, Point goal) {
        super(point.X, point.Y);
        position = point;
        setCameFrome(pathNode);
        this.goal = goal;
        if (pathNode!=null)
            setLengthFromStart(pathNode.getLengthFromStart());
        else setLengthFromStart(-1);
        setLengthToGoal(goal);
        setFullLength(getLengthFromStart()+getLengthToGoal());
    }


    public void setCameFrome(PathNode CameFrome) {
        this.CameFrome = CameFrome;
    }

    public void setLengthFromStart(int i) {
        LengthFromStart = i + 1;
    }

    public int getLengthFromStart() {
        return LengthFromStart;
    }

    public void setLengthToGoal(Point goal)
    {
        LengthToGoal = Math.abs(this.position.X - goal.X) + Math.abs(this.position.Y - goal.Y);
    }

    public int getLengthToGoal()
    {
        return LengthToGoal;
    }

    public void setFullLength(int i)
    {
        this.FullLength=i;
    }

    public int getFullLength() {
        return FullLength;
    }

}
