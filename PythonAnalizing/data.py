import tkinter as tk
from tkinter import ttk
from tkinter import*
from tkinter import messagebox, filedialog
import pymysql
import csv
import mysql.connector
import os
import pandas as pd
import matplotlib.pyplot as plt

mydata=[]

def update(rows):
    global mydata
    mydata=rows
    tv.delete(*tv.get_children())
    for i in rows:
        tv.insert('', 'end', values=i)


def search():
    q2=q.get()
    query="SELECT id, NSS, Nombre, Edad, Peso, Talla, Archivo FROM datos WHERE Nombre LIKE '%"+q2+"%' OR Edad LIKE '%"+q2+"%'"
    cursor.execute(query)
    rows=cursor.fetchall()
    update(rows)


def clear():
    query="SELECT id, NSS, Nombre, Edad, Peso, Talla, Archivo FROM datos"
    cursor.execute(query)
    rows=cursor.fetchall()
    update(rows)
    

def getrow(event):
    rowid=tv.identify_row(event.y)
    item=tv.item(tv.focus())
    #print(item['values'][0])
    t1.set(item['values'][0])
    t2.set(item['values'][1])
    t3.set(item['values'][2])
    t4.set(item['values'][3])
    t5.set(item['values'][4])
    t6.set(item['values'][5])
    t7.set(item['values'][6])
    

def update_usuario():
    NSS=t2.get()
    nombre=t3.get()
    edad=t4.get()
    peso=t5.get()
    talla=t6.get()
    archivo=t7.get()
    pacienteid=t1.get()

    if messagebox.askyesno("Confirm please", "Esta seguro que quiere actualizar el registro?"):
        query="UPDATE datos SET NSS=%s, Nombre=%s, Edad=%s, Peso=%s, Talla=%s, Archivo=%s WHERE id=%s"
        cursor.execute(query, (NSS, nombre, edad, peso, talla, archivo, pacienteid))
        connection.commit()
        clear()
    else:
        return True
    
def add_new():
    NSS=t2.get()
    nombre=t3.get()
    edad=t4.get()
    peso=t5.get()
    talla=t6.get()
    archivo=t7.get()
    query= "INSERT INTO datos (id, NSS, Nombre, Edad, Peso, Talla, Archivo) VALUES(NULL, %s, %s, %s, %s, %s, %s)"
    cursor.execute(query, (NSS, nombre, edad, peso, talla, archivo))
    connection.commit()
    clear()

def delete_usuario():
    usuario_id=t1.get()
    if messagebox.askyesno("Confirm Delete?", "Esta seguro que desea eliminar el registro?"):
        query="DELETE FROM datos WHERE id= " + usuario_id
        cursor.execute(query)
        connection.commit()
        clear()
    else:
        return True



def guardar_CSV():
    if len(mydata) < 1:
        messagebox.showerror("Sin datos", "No hay datos disponibles para exportar")
        return False

    fln=filedialog.asksaveasfilename(initialdir=os.getcwd(), title="Guardar CSV", filetypes=(("CSV File", "*.csv"),("All Files", "*.*")))
    with open(fln, mode='w') as myfile:
        exp_writer=csv.writer(myfile, delimiter=',')
        for i in mydata:
            exp_writer.writerow(i)

    messagebox.showinfo("Exportaciones de datos", "Sus datos se han exportado a "+ os.path.basename(fln)+" successfully.")
    cursor.execute(query)
    connection.commit()

def edad():
    cursor.execute("SELECT AVG(Edad) AS average FROM datos")
    result = cursor.fetchall()
    for i in result:
        print(i[0])
        messagebox.showerror("Promedio de Edades", (i[0]))
    #plt.title("Histograma ")
    #plt.hist(result)
    #plt.grid(True)
    #plt.show()
        


def peso():
    cursor.execute("SELECT AVG(Peso) AS average FROM datos")
    result = cursor.fetchall()
    for i in result:
        print(i[0])
        messagebox.showerror("Promedio del Peso", (i[0]))
        
def talla():
    cursor.execute("SELECT AVG(Talla) AS average FROM datos")
    result = cursor.fetchall()
    for i in result:
        print(i[0])
        messagebox.showerror("Promedio de la talla", (i[0]))

    
#CONEXION db

connection= pymysql.connect(
    host='localhost',
    user='root',
    password='Polic4rpi496',
    database='basicdata'  #nombre de la tabla

)
cursor=connection.cursor()
sql="select *from datos"
cursor.execute(sql)
connection.commit()

resultados=cursor.fetchall()
print(resultados)



root=Tk()
q=StringVar()
t1=StringVar()
t2=StringVar()
t3=StringVar()
t4=StringVar()
t5=StringVar()
t6=StringVar()
t7=StringVar()

frm=Frame(root)
#frm.pack(side=tk.LEFT, padx=20)

wrapper1=LabelFrame(root, text="Lista de Pacientes")
wrapper2=LabelFrame(root, text="Búscar")
wrapper3=LabelFrame(root, text="Insertar Pacient")
wrapper4=LabelFrame(root, text="Datos Estádisticos")

wrapper1.pack(fill="both", expand="yes", padx=20, pady=10)
wrapper2.pack(fill="both", expand="yes", padx=20, pady=10)
wrapper3.pack(fill="both", expand="yes", padx=20, pady=10)
wrapper4.pack(fill="both", expand="yes", padx=20, pady=10)

tv=ttk.Treeview(wrapper1, columns=(1,2,3,4,5,6,7), show="headings", height="10")
tv.pack()

tv.heading(1, text="idPaciente")
tv.heading(2, text="NSS")
tv.heading(3, text="Nombre")
tv.heading(4, text="Edad")
tv.heading(5, text="Peso")
tv.heading(6, text="Talla")
tv.heading(7, text="Archivo")

tv.bind('<Double 1>', getrow)
    



query="SELECT id, NSS, Nombre, Edad, Peso, Talla, Archivo from datos"
cursor.execute(query)
rows=cursor.fetchall()
update(rows)

#SEARCH SECTION

lbl=Label(wrapper2, text="Buscar")
lbl.pack(side=tk.LEFT, padx=10)
ent=Entry(wrapper2, textvariable=q)
ent.pack(side=tk.LEFT, padx=6)
btn=Button(wrapper2, text="Buscar", command=search)
btn.pack(side=tk.LEFT, padx=6)
cbtn=Button(wrapper2, text="Limpiar", command=clear)
cbtn.pack(side=tk.LEFT, padx=6)

#USER DATA SECTION
#lbl1=Label(wrapper3, text="IdPaciente")
#lbl1.grid(row=0, column=0, padx=5, pady=3)
#ent1= Entry(wrapper3, textvariable=t1)
#ent1.grid(row=0, column=1, padx=5, pady=3)

lbl2=Label(wrapper3, text="NSS")
lbl2.grid(row=1, column=0, padx=5, pady=3)
ent2=Entry(wrapper3, textvariable=t2)
ent2.grid(row=1, column=1, padx=5, pady=3)

lbl3=Label(wrapper3, text="Nombre")
lbl3.grid(row=2, column=0, padx=5, pady=3)
ent3=Entry(wrapper3, textvariable=t3)
ent3.grid(row=2, column=1, padx=5, pady=3)

lbl4=Label(wrapper3, text="Edad")
lbl4.grid(row=3, column=0, padx=5, pady=3)
ent4=Entry(wrapper3, textvariable=t4)
ent4.grid(row=3, column=1, padx=5, pady=3)

lbl5=Label(wrapper3, text="Peso")
lbl5.grid(row=4, column=0, padx=5, pady=3)
ent5=Entry(wrapper3, textvariable=t5)
ent5.grid(row=4, column=1, padx=5, pady=3)

lbl6=Label(wrapper3, text="Talla")
lbl6.grid(row=5, column=0, padx=5, pady=3)
ent6=Entry(wrapper3, textvariable=t6)
ent6.grid(row=5, column=1, padx=5, pady=3)

lbl7=Label(wrapper3, text="Archivo")
lbl7.grid(row=6, column=0, padx=5, pady=3)
btn7=Button(wrapper3, text="Guardar CSV", command=guardar_CSV)  #EN ESTA PARTE VA EL COMANDO CSV
btn7.grid(row=6, column=1, padx=5, pady=3)


up_btn=Button(wrapper3, text="Actualizar", command=update_usuario)
add_btn=Button(wrapper3, text="Nuevo", command=add_new)
delete_btn=Button(wrapper3, text="Borrar", command=delete_usuario)

#BOTONES ESTADISTICOS
promedio_edad=Button(wrapper4, text="Promedio Edades", command=edad) #BOTON EDAD
promedio_edad.grid(row=6, column=1, padx=5, pady=3)

promedio_peso=Button(wrapper4, text="Promedio Peso", command=peso) #BOTON PESO
promedio_peso.grid(row=6, column=2, padx=5, pady=3)

promedio_talla=Button(wrapper4, text="Promedio Talla", command=talla) #BOTON TALLA
promedio_talla.grid(row=6, column=3, padx=5, pady=5)


add_btn.grid(row=12, column=0, padx=5, pady=3) #Boton nuevo
#up_btn.grid(row=8, column=1, padx=5, pady=3)
#delete_btn.grid(row=8, column=2, padx=5, pady=3)


root.title("Datos Básicos")
root.geometry("1500x700")
root.mainloop()





