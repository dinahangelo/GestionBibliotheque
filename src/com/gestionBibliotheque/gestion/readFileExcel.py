import openpyxl
import sys

path = sys.argv[1]
excel = openpyxl.load_workbook(path)
feuille = excel["Grand livre"]
i = 1
for cellMatricule, cellPrenom, cellNom in zip(feuille["A"][1:], feuille["B"][1:], feuille["C"][1:]) :
    print("{} : {} {}".format(cellMatricule.value, "" if cellNom.value is None else cellNom.value, "" if cellPrenom.value is None else cellPrenom.value))

# print("success")