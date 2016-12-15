require 'fileutils'

def filter_directories

  excluded_directories = ["bin"]

  all_files = Dir.glob('*')

  return all_files.select do |file| 
    next if excluded_directories.include?(file)
    File.directory?(file)
  end
end

def create_bin
  FileUtils.rm_rf('bin')
  FileUtils.mkdir_p('bin')
end

def run_tests directories
  for directory in directories
    puts "building #{directory}"
    system("javac -d bin #{directory}/*.java")
  end
end

create_bin()

valid_directories = filter_directories()

run_tests(valid_directories)