class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int closestX = clamp(xCenter, x1, x2);
        int closestY = clamp(yCenter, y1, y2);
        
        int distanceX = xCenter - closestX;
        int distanceY = yCenter - closestY;
        
        return (distanceX * distanceX) + (distanceY * distanceY) <= (radius * radius);
    }
    
    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val)); 
    }
}