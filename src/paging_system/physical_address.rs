pub struct PhysicalAddress {
    frame: u128,
    offset: u128,
    original_number: u128,
}


impl PhysicalAddress {
    pub fn new(frame: u128, offset: u128, original_number: u128) -> Self {
        Self { frame, offset, original_number }
    }
}


impl std::fmt::Display for PhysicalAddress {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(
            f,
"---DETALLES DIRECCIÓN FÍSICA---
Contenido: {} / {:b} / {:X}
Marco: {}
Desplazamiento: {}",
            self.original_number,
            self.original_number,
            self.original_number,
            self.frame, self.offset
        )
    }
}
