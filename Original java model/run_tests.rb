require 'find'

def find_valid_files
  files = []
  Find.find('bin') do |path|
    files << path if path.include?(".class") && path.include?("Test")
  end
  return files
end

def run_tests(files)
  for file in files
    fileName = File.basename(file, ".*" )
    puts "Running #{fileName}"

    system("java org.junit.runner.JUnitCore #{fileName}")
  end
end

valid_files = find_valid_files()

Dir.chdir "bin"

run_tests(valid_files)