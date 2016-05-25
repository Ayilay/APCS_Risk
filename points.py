points = [
[892, 128],
[907, 135],
[908, 164],
[883, 181],
[878, 173],
[899, 154],
[891, 128]
]
minx = points[0][0]
miny = points[0][1]

maxx = points[0][0]
maxy = points[0][1]

for p in points:
    if p[0] < minx:
        minx = p[0]
    if p[1] < miny:
        miny = p[1]
    if p[0] > maxx:
        maxx = p[0]
    if p[1] > maxy:
        maxy = p[1]

print "this.setPreferredSize(new Dimension(" + str(maxx - minx) + ", " + str(maxy - miny) + "));"
print "this.setSize(new Dimension(" + str(maxx - minx) + ", " + str(maxy - miny) + "));"
print "this.setLocation(" + str(minx) + ", " + str(miny+5) + ");"

for p in points:
    p[0] -= minx
    p[1] -= miny
    print "shape.addPoint(" + str(p[0]) + ", " + str(p[1]) + ");"

