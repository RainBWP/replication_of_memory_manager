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
"---PAGE DETAILS---:
Content: {} / {:b} / {:X}
Cache disabled bit: {}
Accessed bit: {}
Dirty bit: {}
Permission bit: {}
Present/Absent bit: {}
Frame: {}",
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

/*
1. Bit de caché inhabilitado
2. Bit de referida (o referenciada creo)
3. Bit de modificada
4. Bit de permiso/protección
5. Bit de presente/ausente
*/