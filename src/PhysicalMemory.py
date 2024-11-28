class PhysicalMemory:
    def __init__(self, bit_virt:int, cant_frames:int, num_marco:int, memory:list):
        self.tam_virtual = int(bit_virt)
        self.cantidad_de_paginas = int(cant_frames)
        self.numero_de_marco = int(num_marco)

        self.bits_marco = (self.numero_de_marco - 1).bit_length()
        self.bits_pagina = (self.tam_virtual -1).bit_length()

        self.memoria = memory

    def imprimir_memoria_completa(self)->None:
        print(f'No|A|R|M|P|C|FRAME')
        for i in range(self.cantidad_de_paginas):
            print(f'{i:2}', end='')
            self.imprimir_tabla_pagina(i)
        pass

    def imprimir_memoria_bruto(self, pagina)->None:
        print(f'Pagina {self.memoria[pagina]}')

    def get_memoria_bruto(self, pagina)->int:
        return self.memoria[pagina]
    
    def imprimir_memoria_completa_bruto_binario(self)->None:
        for i in range(len(self.memoria)):
            print(f'{i}\t{self.memoria[i]:b}')

    def imprimir_tabla_pagina(self, pagina) -> None:
        memoryValue = self.memoria[pagina]
        bitsToShift = self.bits_marco
        bit_permiso, bit_referencia, bit_modificado, bit_presente_ausente, bit_cache, num_frame_bin = extraer_bits(memoryValue, bitsToShift)
        print(f'|{bit_permiso}|{bit_referencia}|{bit_modificado}|{bit_presente_ausente}|{bit_cache}|{num_frame_bin}|')

    def get_tabla_formato(self, memoryValue):
        bitsToShift = self.bits_marco
        bit_permiso, bit_referencia, bit_modificado, bit_presente_ausente, bit_cache, num_frame_bin = extraer_bits(memoryValue, bitsToShift)
        print(f'|{bit_permiso}|{bit_referencia}|{bit_modificado}|{bit_presente_ausente}|{bit_cache}|{num_frame_bin}|')

    def get_tabla_pagina(self, pagina) -> tuple:
        num_pag = pagina >> self.bits_pagina
        memoryValue = self.memoria[num_pag]
        bitsToShift = self.bits_marco
        bit_permiso, bit_referencia, bit_modificado, bit_presente_ausente, bit_cache, num_frame_bin = extraer_bits(memoryValue, bitsToShift)
        memoryConverted = {
            'bit_permiso': bit_permiso,
            'bit_referencia': bit_referencia,
            'bit_modificado': bit_modificado,
            'bit_presente_ausente': bit_presente_ausente,
            'bit_cache': bit_cache,
            'num_frame': num_frame_bin
        }
        return memoryConverted

    def desplazamiento_de_pagina(self, pagina):
        pass

    def valor_de_pagina(self, pagina):
        pass

    def get_physical_address_with_virtual_memory(self, virtual_address:int) -> int:
        
        virtual_page = (virtual_address >> (self.bits_pagina))
        virtual_desplazamiento = (virtual_address & ((1 << self.bits_marco) - 1))
        print(f'Direccion Virtual: {virtual_address:b} {virtual_address}')
        print(f'Pagina: {virtual_page:b} Desplazamiento: {virtual_desplazamiento:b}')
        print(f'Pagina: {virtual_page} Desplazamiento: {virtual_desplazamiento}')
        if virtual_page >= self.cantidad_de_paginas:
            print("Error: Pagina fuera de rango")
            return -1
        
        memoryNumber = self.memoria[virtual_page]

        memoryNumber = (memoryNumber >> self.bits_marco) << self.bits_marco
        memoryNumber = memoryNumber | virtual_desplazamiento
        
        return memoryNumber

def extraer_bits(memoryValue, bitsToShift):
    bit_permiso = (memoryValue >> (bitsToShift + 4)) & 1
    bit_referencia = (memoryValue >> (bitsToShift + 3)) & 1
    bit_modificado = (memoryValue >> (bitsToShift + 2)) & 1
    bit_presente_ausente = (memoryValue >> (bitsToShift + 1)) & 1
    bit_cache = (memoryValue >> bitsToShift) & 1
    num_frame = memoryValue & ((1 << bitsToShift) - 1)
    num_frame_bin = ''.join('1' if (num_frame >> i) & 1 else '0' for i in range(bitsToShift - 1, -1, -1))
    return bit_permiso, bit_referencia, bit_modificado, bit_presente_ausente, bit_cache, num_frame_bin