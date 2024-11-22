mod paging_system;
mod util;

use paging_system::prelude::*;
use util::*;

fn main() {
    let file_data = FileData::from_file("entrada.txt").expect("Error en la lectura de datos");

    let mut option: u128;

    let mut input: u128;
    let mut page: Page;
    let mut virtual_address: VirtualAddress;
    let mut physical_address: PhysicalAddress;


    loop {
        clear(); //Limpiar pantalla al principio porque... por qué no?
        println!("----DV_DF----");
        println!("1. Ver información del archivo de entrada");
        println!("2. Ver tabla de páginas");
        println!("3. Traducción de dirección virtual a física");
        println!("4. Salir");

        option = try_input("-------------").unwrap();
        clear();

        match option {
            1 => {
                println!("{}", file_data);
            }
            2 => {
                file_data
                    .get_pages()
                    .iter()
                    .for_each(|page| match page.to_page(&file_data) {
                        Ok(page) => {
                            println!("{}\n", page);
                        }
                        Err(e) => {
                            println!("Error: {}\n", e);
                        }
                    });
            }
            3 => {
                input = try_input("Ingresa la dirección virtual: ").expect("Error en lectura del archivo");

                virtual_address = match input.to_virtual_address(&file_data) {
                    Ok(virtual_address) => virtual_address,
                    Err(e) => {
                        println!("Error: {}", e);
                        pause();
                        continue;
                    }
                };

                page = match virtual_address.get_page(&file_data) {
                    Ok(page)=>page,
                    Err(e)=>{
                        println!("Error: {}",e);
                        pause();
                        continue;
                    }
                };

                physical_address = match virtual_address.to_physical_address(&file_data) {
                    Ok(physical_address) => physical_address,
                    Err(e) => {
                        println!("Error: {}", e);
                        pause();
                        continue;
                    }
                };
                println!("{}\n", virtual_address);
                println!("{}\n",page);
                println!("{}\n", physical_address);
            }
            4 => {
                break;
            }
            _ => {
                println!("Opción {} desconocida ", option);
            }
        }
        pause();
    }
}
