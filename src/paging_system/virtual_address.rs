use super::file_data::*;
use super::page::Page;
use super::physical_address::*;
use super::transform::PagingSystem;


pub struct VirtualAddress {
    page_num: u128,
    offset: u128,
    original_number: u128,
}


impl VirtualAddress {
    pub fn new(page_num: u128, offset: u128, original_number: u128) -> Self {
        Self { page_num, offset, original_number }
    }

    pub fn to_physical_address(&self, file_data: &FileData) -> Result<PhysicalAddress, String> {
        let page = self.get_page(file_data)?;

        let frame = page.get_frame();
        let offset: u128 = self.offset;
        let original_number = (frame << file_data.get_offset_bits()) | offset;

        Ok(PhysicalAddress::new(frame, offset, original_number))
    }

    pub fn get_page(&self, file_data: &FileData)->Result<Page,String>{
        let page = match file_data.get_page(self.page_num as usize) {
            Some(page)=>page,
            None=>{
                return Err(format!("Page number {} not found in page table",self.page_num));
            }
        };
        page.to_page(file_data)
    }
}


impl std::fmt::Display for VirtualAddress {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(
            f,
"---VIRTUAL ADDRESS DETAILS---:
Content: {} / {:b} / {:X}
Page number: {}
Offset: {}",
            self.original_number,
            self.original_number,
            self.original_number,
            self.page_num, self.offset
        )
    }
}