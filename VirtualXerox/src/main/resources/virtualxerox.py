import cv2
import sys
import numpy as np
from matplotlib import pyplot as plt

path="/Users/yugrawal/Documents/workspace-spring-tool-suite-4-4.5.1.RELEASE/VirtualXerox/src/main/resources/static/"+sys.argv[1]
img=cv2.imread(path,0)


img2=img

clahe = cv2.createCLAHE(clipLimit=2.0, tileGridSize=(8,8))
cl1 = clahe.apply(img)
#cl1=cv2.resize(cl1,(400,400))

img2=cv2.equalizeHist(cl1)
#img2=cv2.resize(cl1,(400,400))

img=cv2.cvtColor(img,cv2.COLOR_BGR2RGB)
#img=cv2.resize(img,(400,400))
path2="/Users/yugrawal/Documents/workspace-spring-tool-suite-4-4.5.1.RELEASE/VirtualXerox/src/main/resources/static/copy"+sys.argv[1]
cv2.imwrite(path2,img)


