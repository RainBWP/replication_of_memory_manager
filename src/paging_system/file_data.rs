use std::fs::File;
use std::io::BufRead;

pub struct FileData {
    page_size: u32,
    frame_num: u32,
    page_num: u32,
    pages: Vec<u128>,
}


impl FileData {
    pub fn new(page_size: u32, frame_num: u32, page_num: u32, pages: Vec<u128>) -> Self {
        Self {
            page_size,
            frame_num,
            page_num,
            pages,
        }
    }

    pub fn from_file<P: AsRef<std::path::Path>>(path: P) -> Result<Self, std::io::Error> {
        let file = File::open(path)?;
        let file_input = std::io::BufReader::new(file);

        let mut pages = Vec::<u128>::new();

        let mut page_size: u32 = 0;
        let mut frame_num: u32 = 0;
        let mut page_num: u32 = 0;

        file_input.lines().enumerate().for_each(|(index, line)| {
            if let Ok(line) = line {
                match index {
                    0 => {
                        if let Some(number) = line.split_whitespace().last() {
                            if let Ok(number) = number.parse::<u32>() {
                                page_size = number;
                            }
                        }
                    }
                    1 => {
                        if let Some(number) = line.split_whitespace().last() {
                            if let Ok(number) = number.parse::<u32>() {
                                frame_num = number;
                            }
                        }
                    }
                    2 => {
                        if let Some(number) = line.split_whitespace().last() {
                            if let Ok(number) = number.parse::<u32>() {
                                page_num = number;
                            }
                        }
                    }
                    _ => {
                        if let Ok(num) = line.trim().parse::<u128>() {
                            pages.push(num);
                        }
                    }
                }
            }
        });
        Ok(Self::new(page_size, frame_num, page_num, pages))
    }

    pub fn get_page(&self, index: usize) -> Option<&u128> {
        self.pages.get(index)
    }

    pub fn get_pages(&self) -> Vec<u128> {
        self.pages.clone()
    }

    pub fn get_page_bits(&self) -> u8 {
        (self.page_num as f64).log2().ceil() as u8
    }

    pub fn get_frame_bits(&self) -> u8 {
        (self.frame_num as f64).log2().ceil() as u8
    }

    pub fn get_offset_bits(&self) -> u8 {
        (self.page_size as f64).log2().ceil() as u8
    }

    pub fn get_page_size(&self)->u32{
        self.page_size
    }
    pub fn get_frame_num(&self)->u32{
        self.frame_num
    }
    pub fn get_page_num(&self)->u32{
        self.page_num
    }

}


impl std::fmt::Display for FileData {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(
            f,
"---Datos archivo de entrada---
Tamaño de página: {}
Número de marcos: {}
Número de páginas: {}
Páginas: {:?}
",
            self.page_size, self.frame_num, self.page_num, self.pages
        )
    }
}
