pub mod page;
pub mod virtual_address;
pub mod physical_address;
pub mod file_data;
pub mod transform;

pub mod prelude{
    pub use super::page::Page;
    pub use super::virtual_address::VirtualAddress;
    pub use super::physical_address::PhysicalAddress;
    pub use super::file_data::FileData;
    pub use super::transform::PagingSystem;
}