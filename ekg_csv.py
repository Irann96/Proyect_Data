import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

df=pd.read_csv('ekg.csv', header=0)
#df.head()
#print(df)

print(df.info())
print(df.head())
print(df.iloc[0:2])
#print(df.iloc[:,0:1])

t=(df['Tiempo (s)'])
EKG=(df['EKG (mV)'])
plt.plot(t,EKG)
plt.title("Signal EKG")
plt.xlabel("Tiempo")
plt.ylabel("mV")
plt.show()
