use super::file_data::FileData;
use super::page::Page;
use super::virtual_address::VirtualAddress;
use super::physical_address::PhysicalAddress;


pub trait PagingSystem {
    fn to_page(&self, file_data: &FileData) -> Result<Page, String>;
    fn to_virtual_address(&self, file_data: &FileData) -> Result<VirtualAddress, String>;
    fn to_physical_address(&self, file_data: &FileData) -> Result<PhysicalAddress, String>;
}


impl PagingSystem for u128 {

    fn to_page(&self, file_data: &FileData) -> Result<Page, String> {
        let frame_bits = file_data.get_frame_bits();

        //let status_mask = (1_u128 << 5)-1;
        let frame_mask = (1_u128 << frame_bits) - 1;

        let status = self >> frame_bits; //Anteriormente let status = ((self >> frame_bits) & status_mask) as u8;
        let frame = self & frame_mask;

        if status >= 32 {
            return Err(format!(
                "Value {} / {:b} is not a valid page content, used bits are above limits",
                self, self
            ));
        }
        if frame >= (file_data.get_frame_num() as u128) {
            return Err(format!("Value {} / {:b}-{:b} / {}-{} is not a valid page content, frame value = {}, frame limit = {}",self,status,frame,status,frame,frame,file_data.get_frame_num()));
        }

        Ok(Page::new(status as u8, frame, *self))
    }

    fn to_virtual_address(&self, file_data: &FileData) -> Result<VirtualAddress, String> {
        let offset_bits = file_data.get_offset_bits();
        //let page_bits = file_data.get_page_bits();

        //let page_mask = (1_u128 << page_bits)-1;
        let offset_mask = (1_u128 << offset_bits) - 1;

        let page = self >> offset_bits; //Anteriormente let page = (self >> offset_bits) & page_mask;
        let offset = self & offset_mask;

        if page >= (file_data.get_page_num() as u128) {
            return Err(format!("Value {} / {:b}-{:b} / {}-{} is not a valid virtual address, page = {}, page limit = {}",self,page,offset,page,offset,page,file_data.get_page_num()));
        }
        if offset >= (file_data.get_page_size() as u128) {
            return Err(format!("Value {} / {:b}-{:b} / {}-{} is not a valid virtual address, offset = {}, offset limit = {}",self,page,offset,page,offset,offset,file_data.get_page_size()));
        }

        Ok(VirtualAddress::new(page, offset, *self))
    }

    fn to_physical_address(&self, file_data: &FileData) -> Result<PhysicalAddress, String> {
        let offset_bits = file_data.get_offset_bits();
        //let frame_bits = file_data.get_frame_bits();

        //let frame_mask = (1_u128 << frame_bits)-1;
        let offset_mask = (1_u128 << offset_bits) - 1;

        let frame = self >> offset_bits; //Anteriormente let frame = (self >> offset_bits) & frame_mask;
        let offset = self & offset_mask;

        if frame >= (file_data.get_frame_num() as u128) {
            return Err(format!("Value {} / {:b}-{:b} / {}-{} is not a valid virtual address, frame = {}, frame limit = {}",self,frame,offset,frame,offset,frame,file_data.get_frame_num()));
        }
        if offset >= (file_data.get_page_size() as u128) {
            return Err(format!("Value {} / {:b}-{:b} / {}-{} is not a valid virtual address, offset = {}, offset limit = {}",self,frame,offset,frame,offset,offset,file_data.get_page_size()));
        }


        Ok(PhysicalAddress::new(frame, offset, *self))
    }
}
