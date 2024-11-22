pub struct Page {
    status: u8,
    frame: u128,
    original_number: u128,
}

impl Page {
    pub fn new(status: u8, frame: u128, original_number: u128) -> Self {
        Self { status, frame, original_number }
    }
    pub fn get_frame(&self) -> u128 {
        self.frame
    }
}

impl std::fmt::Display for Page {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(
            f,
"---DETALLES DE PÁGINA---
Contenido: {} / {:b} / {:X}
Bit de permiso: {}
Bit de referencia: {}
Bit de modificado: {}
Bit de presente/ausente: {}
Bit de caché inhabilitado: {}
Marco: {}",
            self.original_number,
            self.original_number,
            self.original_number,
            (self.status & 0b10000) != 0,
            (self.status & 0b01000) != 0,
            (self.status & 0b00100) != 0,
            (self.status & 0b00010) != 0,
            (self.status & 0b00001) != 0,
            self.frame
        )
    }
}
