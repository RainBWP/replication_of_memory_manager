import argparse
import PhysicalMemory
def get_args()->argparse.Namespace:
        parser = argparse.ArgumentParser(description="Replication of Memory Manager")
        # Add arguments here
        parser.add_argument("-f", "--file", help="Archivo que lee", required=True)
        return parser.parse_args()

def convert_file(file: str) -> dict:
    file_data = []
    with open(file, 'r') as f:
        lines = f.readlines()
        importantData = lines.pop(0)
        for line in lines:
            file_data.append(int(line.strip()))

    returnData = {
        'importantData': importantData,
        'file_data': file_data
    }
    return returnData


def main():
    args = get_args() # obtener direccion del archivo
    file_data = convert_file(args.file) # convertir archivo a lista

    bits_virtual, cantidad_paginas, numero_marcos = file_data['importantData'].split(' ') # obtener los valores de la primera linea

    Physical_Memory = PhysicalMemory.PhysicalMemory(bits_virtual, cantidad_paginas, numero_marcos, file_data['file_data']) # instanciar la clase PhysicalMemory con los valores obtenidos,
    
    Physical_Memory.imprimir_memoria_completa() # imprimir la memoria completa
    
    inputValue = int(input("Direccion Virtual a Traducir (base 10): "))
    VirtualDirection = Physical_Memory.get_physical_address_with_virtual_memory(inputValue)
    print(VirtualDirection)

    if VirtualDirection != -1:
        tablaPoints = Physical_Memory.get_tabla_pagina(VirtualDirection)
        print(f'Direccion Virtual: {VirtualDirection:bin} {VirtualDirection} {VirtualDirection:hex} {VirtualDirection:oct}\n\n Direccion Fisica: {numOnTablaPoints} ',end='')
        print(f'|{tablaPoints.bit_permiso}|{tablaPoints.bit_referencia}|{tablaPoints.bit_modificado}|{tablaPoints.bit_presente_ausente}|{tablaPoints.bit_cache}|{tablaPoints.num_frame_bin}|',end=' ')
        # print(f'{numOnTablaPoints:hex} {numOnTablaPoints:oct}')
    else:
        print("Fin del programa")
        exit()

if __name__ == "__main__":
    
    main()